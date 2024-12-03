package net.scit.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring5.dto.PhoneDTO;
import net.scit.spring5.service.PhoneService;

@Controller
@RequestMapping("/phone")
@Slf4j
@RequiredArgsConstructor
public class PhoneController {
	
	public final PhoneService phoneService;

	@GetMapping("/regist")
	public String regist() {
		
		return "phone/registView";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute PhoneDTO phoneDTO) {
		log.info("============{}", phoneDTO.toString());
		
		phoneService.insert(phoneDTO);
		
		return "phone/registView";
	}
}
