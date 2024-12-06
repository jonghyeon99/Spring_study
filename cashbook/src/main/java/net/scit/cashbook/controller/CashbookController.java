package net.scit.cashbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import net.scit.cashbook.Service.CashbookService;
import net.scit.cashbook.dto.CashbookDTO;

@Controller
@RequestMapping("/cashbook")
@RequiredArgsConstructor
public class CashbookController {
	
	private final CashbookService cashbookService;
	
	@GetMapping("/recode")
	public String recode(Model model) {
		List<CashbookDTO> list = cashbookService.selectAll();
		
		model.addAttribute("list", list);
		return "cashbook/recode";
	}
	
	@PostMapping("/recode")
	public String recode(@ModelAttribute CashbookDTO cashbookDTO) {
		cashbookService.insert(cashbookDTO);
		return "redirect:/cashbook/recode";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id") Long cash_seq) {
		cashbookService.delete(cash_seq);
		return "redirect:/cashbook/recode";
	}
}
