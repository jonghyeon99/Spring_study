package net.scit.carsharing.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.UserDTO;
import net.scit.carsharing.entity.UserEntity;
import net.scit.carsharing.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 1) 회원가입
    public void joinProc(UserDTO userDTO) {
        UserEntity entity = UserEntity.toEntity(userDTO);
        // 비밀번호 암호화 후 저장
        entity.setUserPw(bCryptPasswordEncoder.encode(entity.getUserPw()));
        log.info("회원가입 엔티티: {}", entity.toString());
        repository.save(entity);
    }

    // 2) 아이디 중복확인
    public boolean idCheck(String userId) {
        boolean exists = repository.existsByUserId(userId);
        // DB에 있으면 true
        return !exists; // “없을 때 true”를 반환(Front에서 "사용가능"로 쓰기 위함)
    }
}
