package net.scit.carsharing.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.scit.carsharing.dto.SharingCarDTO;
import net.scit.carsharing.entity.SharingCarEntity;
import net.scit.carsharing.repository.SharingCarRepository;

@Service
@RequiredArgsConstructor
public class SharingCarService {
	private final SharingCarRepository sharingCarRepository;

	/**
	 * 자동차 목록 요청
	 * @param 
	 */
	public List<SharingCarDTO> selectAll() {
		List<SharingCarEntity> temp = sharingCarRepository.findAll();
		List<SharingCarDTO> list = new ArrayList<>();

		temp.forEach((entity) -> list.add(SharingCarDTO.toDTO(entity )));

		return list;
	}
}
