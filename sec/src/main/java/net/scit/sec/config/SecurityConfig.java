package net.scit.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration			// 이 파일이 설정파일임을 나타내는 Annotation
@EnableWebSecurity		// 프로젝트가 스프링 시큐리티로 관리됨을 나타내는 Annotation
public class SecurityConfig {

	
	
	// 요청 URL을 제어
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth) -> auth
					.requestMatchers(
							"/"
							, "/login"
							, "/join"
							, "/joinProc"
							, "/idCheck"
							, "/images/**"
							, "/js/**"
							, "/css/**").permitAll() // "/"와 "/login" 경로는 로그인을 하지 않아도 접근할 수 있다.
					.requestMatchers("/admin").hasRole("ADMIN")
					.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
					.anyRequest().authenticated());   // 가장 마지막. 기타 다른 경로는 로그인을 해야 접근 가능
		
		// Security가 제공해주는 기본 폼을 사용하지 않고 custom login 페이지
		http
			.formLogin((auth) -> auth
					.loginPage("/login")    			// 로그인 없이 특정페이지에 접속을 시도하면 무조건 로그인 페이지로 리다이렉팅
					.loginProcessingUrl("/loginProc") 	// 로그인화면에서 버튼을 클릭하면 시큐리티가 받아서 처리해주는 경로
					.usernameParameter("userId")		// Security가 사용하는 파라미터 대신 개발자가 설정한 파라미터 사용
					.passwordParameter("userPwd")
					.defaultSuccessUrl("/")             // 로그인 성공 시
					.failureUrl("/login?error=true") 	// 로그인 실패 시
					.permitAll());
		
		// logout
		http
			.logout((auth) -> auth
					.logoutUrl("/logout")				// 로그아웃 요청 URL
					.logoutSuccessUrl("/")				// 로그아웃 성공시 URL
					.invalidateHttpSession(true));		// 세션 무효화
		
		// 시큐리티는 사이트 위변조를 방어하도록 설정되어 있음
		// 개발할 때에는 위변조 방어(CSRF)를 disable시키고 배포시 enable 시킴
		http
			.csrf((auth) -> auth.disable());
		
		return http.build();	
	}
	
	// 비밀번호는 암호화해서 저장할 수 있도록 설정 
	// 양방향(공개키 암호화), 단방향(비밀키 암호화)
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}







