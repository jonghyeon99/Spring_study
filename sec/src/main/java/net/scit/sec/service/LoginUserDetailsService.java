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
	private final UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// Repository로 연결하는 코드
		// UserDetails는 DTO의 한 종류
		UserEntity userEntity = repository.findByUserId(userId);
		log.info("{}", userEntity.getUserName());
		LoginUserDetails userDetails = LoginUserDetails.toDTO(userEntity);
		return userDetails;
	}

}
