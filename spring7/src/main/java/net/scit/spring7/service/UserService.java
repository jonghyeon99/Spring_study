package net.scit.spring7.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.UserDTO;
import net.scit.spring7.entity.UserEntity;
import net.scit.spring7.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * 전달받은 userId가 DB에 존재하는지 여부 확인
	 */
	public boolean existId(String userId) {
		boolean result = userRepository.existsById(userId);
		log.info("아이디 존재여부 : {}", result);	// 가입하려면 false
		return !result;
	}

	/*
	 * 회원 가입 처리
	 */
	public boolean joinProc(UserDTO userDTO) {
		// 비밀번호 암호화
		userDTO.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
		
		UserEntity entity = UserEntity.toEntity(userDTO);
		userRepository.save(entity);	// 회원가입 완료
		
		boolean result = userRepository.existsById(userDTO.getUserId());
		
		return result;
	}

}
