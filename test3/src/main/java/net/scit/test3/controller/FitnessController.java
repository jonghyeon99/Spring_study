package net.scit.test3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@PostMapping("/regist")
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
	
	@GetMapping("/selectOne")
	public String selectOne(@RequestParam(name="id") Long id, Model model) {
		FitnessDTO fitnessDTO = fitnessService.selectOne(id);
		
		model.addAttribute("fitness", fitnessDTO);
		
		return "fitness/selectOne";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id") Long id) {
		fitnessService.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(name="id") Long id, Model model) {
		FitnessDTO phoneDTO = fitnessService.selectOne(id);
		
		model.addAttribute("fitness", phoneDTO);
		
		return "fitness/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute FitnessDTO fitnessDTO, RedirectAttributes rttr) {
		fitnessService.update(fitnessDTO);
		
		rttr.addAttribute("id", fitnessDTO.getId());
		return "redirect:/fitness/selectAll";
	}
}
