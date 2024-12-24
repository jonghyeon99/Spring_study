package net.scit.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration			// 설정 파일임을 나타내는 Annotation
@EnableWebSecurity		// 스프링 시큐리티로 관리됨을 나타내는 Annotation
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers(
						"/"
						, "/join"
						, "/joinProc"
						, "/idCheck"
						, "/favicon.ico"
						, "/images/**"
						, "/js/**"
						, "/css/**").permitAll()						// 인증절차없이 접근 
				.requestMatchers("/admin").hasRole("ADMIN")				// ADMIM으로 인증될 때만 접근 가능
				.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")	//  /my로 시작하는 요청은 ADMIN과 USER만 접속
				.anyRequest().authenticated());		  					// 기타 다른 경로는 인증된 사용자만 접근 가능
		
		// Custom 로그인
		http.formLogin((auth) -> auth
				//.loginPage("/login")				// 로그인 화면 요청
				.loginProcessingUrl("/loginProc")	// 로그인 처리 요청
				.usernameParameter("userId")		// 사용자가 재정의한 아이디 파라미터
				.passwordParameter("userPwd")		// 사용자가 재정의한 비번 파라미터
				.defaultSuccessUrl("/")
				.permitAll());
		
		// POST 요청 시 CSRF(Cross-Site Request Forgery) 토큰을 요청하는데, 비활성화함
		http.csrf((auth) -> auth.disable());
		return http.build();	
	}
	
	// 비밀번호 암호화
	// 양방향 암호화(공개키 암호화)
	// 단방향 암호화(Security에서 사용하는 암호화 방식) - 비밀키 암호화
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
