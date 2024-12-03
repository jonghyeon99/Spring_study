package net.scit.spring5.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring5.dto.PhoneDTO;
import net.scit.spring5.entity.PhoneEntity;
import net.scit.spring5.repository.PhoneRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneService {
	
	private final PhoneRepository repository;
	
	public void insert(PhoneDTO phoneDTO) {
		log.info("----------{}", phoneDTO.toString());
		
		PhoneEntity phoneEntity = PhoneEntity.toEntity(phoneDTO);
		
		repository.save(phoneEntity);	// 전달인자로 반드시 entity 타입이 와야 함
	}

	/*
	 * 전달받은 아이디의 정보를 DB에서 조회한다.
	 * 조회된 entity를 dto로 변환해서 반환
	 */
	public PhoneDTO selectOne(Integer id) {
		Optional<PhoneEntity> temp = repository.findById(id);
		
		if(temp.isPresent()) {
			PhoneEntity entity = temp.get();
			
			// entity를 dto로 변경
			PhoneDTO phoneDTO = PhoneDTO.toDTO(entity);
			return phoneDTO;
			
		}
		return null;
	}

	/*
	 * 전달받은 id를 DB에서 삭제하는 요청
	 */
	public void delete(Integer id) {
		repository.deleteById(id);
		
	}

	@Transactional
	public void update(PhoneDTO phoneDTO) {
		// 수정을 할 때는 그냥 save() 하지 말고, findByid로 데이터가 있는지 확인부터 하기
		Optional<PhoneEntity> temp = repository.findById(phoneDTO.getId());
		
		if (temp.isPresent()) {
			PhoneEntity entity = PhoneEntity.toEntity(phoneDTO);
			repository.save(entity);
		}
	}
}
