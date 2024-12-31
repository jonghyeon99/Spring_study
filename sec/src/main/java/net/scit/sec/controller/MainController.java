package net.scit.sec.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.scit.sec.dto.LoginUserDetails;

@Controller
public class MainController {

	@GetMapping({"/", ""})
	public String index(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, Model model
			) {
		
		if(loginUser != null) {
			String loginName = loginUser.getUserName();   // 실명
			String loginId   = loginUser.getUsername();	  // 아이디

			model.addAttribute("loginName", loginName);
			model.addAttribute("loginId", loginId);
		}
		
		return "index";
	}
}
