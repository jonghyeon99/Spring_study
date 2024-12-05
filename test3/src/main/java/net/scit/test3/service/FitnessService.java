package net.scit.test3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

	public List<FitnessDTO> selectAll() {
		List<FitnessEntity> entityList = repository.findAll(Sort.by(Sort.Direction.ASC, "fname"));
		List<FitnessDTO> dtoList = new ArrayList<>();
		
		entityList.forEach((entity) -> dtoList.add(FitnessDTO.toDTO(entity)));
		
		return dtoList;
	}
	
	
}
