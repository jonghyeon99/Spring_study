package net.scit.carsharing.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.scit.carsharing.dto.LoginUserDetails;
import net.scit.carsharing.entity.SharingUserEntity;
import net.scit.carsharing.repository.SharingUserRepository;

@RequiredArgsConstructor
@Service
public class LoginUserDetailsService implements UserDetailsService {
	private final SharingUserRepository userRepository;

	// UserId 검증 로직 추가. DB 테이블에서 데이터를 가져옴
	// 사용자가 login을 하면 SecurityConfig 가로챈 후 이쪽으로 데이터를 전달
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		SharingUserEntity userEntity = userRepository.findById(userId)
				.orElseThrow(() -> {
					throw new UsernameNotFoundException("error 발생");
				});

		// 반환을 UserDetails로 반환해야 하므로 UserDTO를 UserDetails로 바꿈
		return LoginUserDetails.toDTO(userEntity);
	}
}
