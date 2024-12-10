package net.scit.jquery_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.scit.jquery_test.dto.MemberDTO;

@Controller
public class MainController {

	@GetMapping({"/", ""})
	public String index() {
		return "index";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute MemberDTO member, Model model) {
		System.out.println(member);
		
		model.addAttribute("member", member);
		return "index";
	}
}

// 아이디(String), 이메일(String), 사는 동네(String), 나이(int): 20~50