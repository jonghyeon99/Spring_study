package net.scit.test2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.scit.test2.dto.StockDTO;


@Slf4j
@Controller
@RequestMapping("/stock")
public class StockController {

	@GetMapping("/stockInsert")
	public String stockInsert() {
		return "stock/stockInsert";	// forwarding
	}
	
	@PostMapping("/stockProc")
	public String stockProc(@ModelAttribute StockDTO stockDTO, Model model) {
		log.info("stockDTO : {}", stockDTO.toString());
		
		model.addAttribute("stock", stockDTO);
		
		return "stock/stockResult";	// 반드시 forwarding으로 반환해야 출력할 수 있다!
	}
}
