package net.scit.spring5.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.scit.spring5.dto.PhoneDTO;

@Service
@Slf4j
public class PhoneService {

	public void insert(PhoneDTO phoneDTO) {
		log.info("----------{}", phoneDTO.toString());
	}
}
