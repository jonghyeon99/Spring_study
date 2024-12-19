package net.scit.guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import net.scit.guestbook.dto.GuestbookDTO;
import net.scit.guestbook.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {
	
	private final GuestbookService guestbookService;

	@GetMapping("/guestbookRegist")
	public String regist() {
		return "regist";
	}
	
	@PostMapping("/guestbookRegist")
	@ResponseBody
	public String regist(@ModelAttribute GuestbookDTO guestbookDTO) {
		guestbookService.insert(guestbookDTO);
		return "OK";
	}
	
	@GetMapping("/selectAll")
	@ResponseBody
	public List<GuestbookDTO> selectAll() {
		List<GuestbookDTO> list = guestbookService.selectAll();
		return list;
	}
	
	@GetMapping("/delete")
	@ResponseBody
	public String delete(@ModelAttribute GuestbookDTO guestbookDTO) {
		guestbookService.delete(guestbookDTO);
		return "OK";
	}
	
	@PostMapping("/update")
	public String update() {
		return "guestbook/delete";
	}
	
	@GetMapping("/selectOne")
	public String selectOne() {
		return "guestbook/selectAll";
	}
}
