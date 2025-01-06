package net.scit.spring7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers(
								"/"
								, "/board/boardList"
								, "/board/boardDetail"
								, "/user/join"
								, "/user/joinProc"
								, "/user/idCheck"
								, "/user/login"
								, "/images/**"
								, "/css/**"
								, "/js/**")
						.permitAll()
						.requestMatchers("/admin").hasRole("ADMIN")
						.requestMatchers("/mypage/**").hasAnyRole("ADMIN", "USER")
						.anyRequest().authenticated());

		// Custom Login 설정
		http
			.formLogin((auth) -> auth
					.loginPage("/user/login")
					.loginProcessingUrl("/user/loginProc")
					.usernameParameter("userId")
					.passwordParameter("userPwd")
					.failureUrl("/user/login?error=true")
					.permitAll());

		// logout 설정
		http
			.logout((auth) -> auth
					.logoutUrl("/user/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.clearAuthentication(true));

		// POST 요청시 CSRF 토큰을 요청하므로 (Cross-Site Request Forgery) 비활성화(개발환경)
		http
			.csrf((auth) -> auth.disable());

		return http.build();
	}

	// 단방향 비밀번호 암호화
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
