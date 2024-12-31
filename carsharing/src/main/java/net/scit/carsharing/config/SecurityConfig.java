package net.scit.carsharing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration            // 설정 파일임
@EnableWebSecurity        // 스프링 시큐리티 활성화
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1) 인증/인가 설정
        http.authorizeHttpRequests(auth -> auth
            // 접속 권한
            .requestMatchers(
                "/", 
                "/index",
                "/user/join", 
                "/user/joinProc", 
                "/user/idCheck",
                "/js/**", 
                "/css/**"
            ).permitAll()                  // 로그인 안 해도 접근 가능
            .requestMatchers("/admin").hasRole("ADMIN")
            .requestMatchers("/car/**").hasAnyRole("ADMIN", "USER")
            .anyRequest().authenticated() // 그 외 경로는 인증 필요
        );

        // 2) 폼 로그인 사용
        http.formLogin((auth) -> auth
								 .loginPage("/user/login")
								 .loginProcessingUrl("/user/loginProc")
								 .usernameParameter("userId")
								 .passwordParameter("userPw")
								 .defaultSuccessUrl("/")
								 .failureUrl("/user/login?error=true")
								 .permitAll()
				);

        // 3) 로그아웃 설정
        http.logout((logout) -> logout
            .logoutUrl("/user/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
        );

        // 4) 개발 단계에서 CSRF 비활성화
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    // 비밀번호 암호화용
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
