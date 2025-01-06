package net.scit.spring7.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.LoginUserDetails;
import net.scit.spring7.entity.UserEntity;
import net.scit.spring7.repository.UserRepository;

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
		// Optional 반환하는 메소드 orElseThrow() 연결해서 사용가능
		UserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("ID나 비밀번호가 틀렸습니다.");
				});
		return LoginUserDetails.toDTO(userEntity);
	}

}




