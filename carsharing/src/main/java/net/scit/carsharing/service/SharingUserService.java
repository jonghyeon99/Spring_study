package net.scit.carsharing.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.scit.carsharing.dto.SharingUserDTO;
import net.scit.carsharing.entity.SharingUserEntity;
import net.scit.carsharing.repository.SharingUserRepository;

@Service
@RequiredArgsConstructor
public class SharingUserService {
    private final SharingUserRepository sharingUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 가입
     * @param sharingUserDTO
     */
    public void insert(SharingUserDTO sharingUserDTO) {
        SharingUserEntity entity = SharingUserEntity.toEntity(sharingUserDTO);
        entity.setUserPwd(bCryptPasswordEncoder.encode(entity.getUserPwd()));
        
        sharingUserRepository.save(entity);
    }

    /**
     * 회원 조회: 조회된 회원의 정보를 반환한다.
     * @param userId
     * @return
     */
    public SharingUserDTO selectUser(String userId) {
        Optional<SharingUserEntity> existed = sharingUserRepository.findById(userId);

        if (!existed.isPresent()) {
            return null;
        }
        SharingUserDTO userDTO = SharingUserDTO.toDTO(existed.get());

        return userDTO;
    }
}
