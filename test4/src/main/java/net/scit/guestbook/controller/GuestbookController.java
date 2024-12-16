package net.scit.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@GetMapping("/guestbookRegist")
	public String guestbookRegist() {
		return "guestbook/guestbookRegist";
	}
	
	@GetMapping("/selectAll")
	public String selectAll() {
		return "guestbook/selectAll";
	}
	
	@PostMapping("/insert")
	public String insert() {
		return "guestbook/insert";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return "guestbook/delete";
	}
}
