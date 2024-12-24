package net.scit.sec.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.sec.dto.UserDTO;
import net.scit.sec.entity.UserEntity;
import net.scit.sec.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	 * 전달받은 userDTO를 Entity로 변환한 후 DB에 저장
	 */
	public void joinProc(UserDTO userDTO) {
		UserEntity entity = UserEntity.toEntity(userDTO);
		entity.setUserPwd(bCryptPasswordEncoder.encode(entity.getUserPwd()));
		
		log.info("---- {}", entity.toString());
		repository.save(entity);
	}
	/*
	 * 전달받은 userId값이 DB에 존재하는지 확인코드
	 */
	public boolean idCheck(String userId) {
		boolean result = repository.existsByUserId(userId);
		
		System.out.println("===========" + result); // 있는 아이디 t, 없는 아이디 f
		return !result;		// 없는 아이디를 사용해야 하므로 Front에서는 true로 전달
	}
}
