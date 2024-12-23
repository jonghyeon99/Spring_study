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
}
