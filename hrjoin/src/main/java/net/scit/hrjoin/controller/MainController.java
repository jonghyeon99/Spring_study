package net.scit.hrjoin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import net.scit.hrjoin.dto.EmployeeDTO;
import net.scit.hrjoin.service.EmployeeService;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final EmployeeService service;
	
	@GetMapping({"/", ""})
	public String index(Model model) {
		
		List<EmployeeDTO> list = service.selectAll();
		
		model.addAttribute("list", list);
		
		return "index";
	}
}
