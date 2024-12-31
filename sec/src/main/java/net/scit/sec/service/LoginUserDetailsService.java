package net.scit.sec.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.sec.dto.LoginUserDetails;
import net.scit.sec.entity.UserEntity;
import net.scit.sec.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	
	// 오버라이드는 상속받은 메소드를 다시 재정의하는 행위
	// 매개변수명과 접근지정자보다 큰 지정자로 바꾸는 것만 가능
	// loadUserByUsername() 비밀번호 비교는 명시적으로 없다!
	@Override
	public UserDetails loadUserByUsername(String userId) 
			throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		log.info("로그인 아이디: {} ", userId );
		if(userEntity != null) {   // 올바른 값을 입력
			// 일반 DTO로 변환하면 안되오!
			// LoginUserDetails로 변환해서 반환해야함!
			LoginUserDetails userDTO = LoginUserDetails.toDTO(userEntity);
			
			return userDTO;
		} else {
			throw new UsernameNotFoundException("오류났지!!"); // 이 메시지를 사용하려면 클래스를 만들어야 한다.
		}
	}

}




