package net.scit.test3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.test3.dto.FitnessDTO;
import net.scit.test3.service.FitnessService;

@Controller
@RequestMapping("/fitness")
@Slf4j
@RequiredArgsConstructor
public class FitnessController {

	private final FitnessService fitnessService;
	/*
	 * 회원 등록을 위한 화면 요청
	 */
	@GetMapping("/regist")
	public String regist() {
		return "fitness/regist";
	}
	
	/*
	 * 회원 등록 처리
	 */
	@PostMapping("regist")
	public String regist(@ModelAttribute FitnessDTO fitnessDTO) {
		fitnessService.insert(fitnessDTO);
		return "fitness/regist";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		List<FitnessDTO> list = fitnessService.selectAll();
		
		model.addAttribute("list", list);
		
		return "fitness/selectAll";
	}
}
