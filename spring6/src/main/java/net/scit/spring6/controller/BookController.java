package net.scit.spring6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.service.BookService;

@Controller
@RequestMapping("/reading")
@RequiredArgsConstructor
public class BookController {
	
	private final BookService service;

	@GetMapping("/bookRegist")
	public String bookRegist(Model model) {
		List<BookDTO> list = service.selectAll();
		model.addAttribute("list", list);
		
		return "/reading/bookRegist";
	}
	
	@PostMapping("/bookRegist")
	public String bookRegist(@ModelAttribute BookDTO bookDTO, Model model) {
		// 데이터 저장
		service.insert(bookDTO);

		// 등록 후 리스트 갱신
		List<BookDTO> list = service.selectAll();
		model.addAttribute("list", list);

		// 리스트와 함께 같은 뷰로 이동
		return "/reading/bookRegist";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "bookSeq") Integer bookSeq) {
		
		service.delete(bookSeq);

		return "redirect:/reading/bookRegist";
	}
}
