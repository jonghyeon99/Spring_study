package net.scit.test3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.test3.dto.FitnessDTO;
import net.scit.test3.entity.FitnessEntity;
import net.scit.test3.repository.FitnessRepository;
@RequiredArgsConstructor
@Service
@Slf4j
public class FitnessService {
	private final FitnessRepository repository;
	
	public void insert(FitnessDTO fitnessDTO) {
		log.info("----------{}", fitnessDTO.toString());
		
		FitnessEntity fitnessEntity = FitnessEntity.toEntity(fitnessDTO);
		
		repository.save(fitnessEntity);
	}
	
	public FitnessDTO selectOne(Long id) {
		Optional<FitnessEntity> temp = repository.findById(id);
		
		if(temp.isPresent()) {
			FitnessEntity entity = temp.get();
			
			// entity를 dto로 변경
			FitnessDTO fitnessDTO = FitnessDTO.toDTO(entity);
			return fitnessDTO;
			
		}
		return null;
	}

	public List<FitnessDTO> selectAll() {
		List<FitnessEntity> temp = repository.findAll(Sort.by(Sort.Direction.ASC, "fname"));
		List<FitnessDTO> list = new ArrayList<>();
		
		for(FitnessEntity entity : temp) {
			FitnessDTO dto = FitnessDTO.toDTO(entity);
			
			dto.setWeight(entity.getWeight());
			dto.setHeight(entity.getHeight());
			
			list.add(dto);
		}
		
		return list;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public void update(FitnessDTO fitnessDTO) {
		// 수정을 할 때는 그냥 save() 하지 말고, findByid로 데이터가 있는지 확인부터 하기
		Optional<FitnessEntity> temp = repository.findById(fitnessDTO.getId());
		
		if (temp.isPresent()) {
			FitnessEntity entity = FitnessEntity.toEntity(fitnessDTO);
			repository.save(entity);
		}
	}
	
}
