package net.scit.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.scit.spring3.dto.JoinDTO;

@Controller
@RequestMapping("/user")
public class JoinController {
	/*
	    회원가입을 위한 화면을 요청
	*/
	@GetMapping("/joinView")	// end point
	public String joinView() {
		return "member/joinView";
	}
	
	@PostMapping("/joinProc")
	public String joinProc(
//			@RequestParam(name="userid") String userid
//			, @RequestParam(name="userpwd") String userpwd
//			, @RequestParam(name="email") String email
//			, @RequestParam(name="gender", defaultValue = "남성") String gender
//			, @RequestParam(name="sports", defaultValue= "None") String sports
			@ModelAttribute JoinDTO joinDTO
			) {
//		System.out.println("아이디: " + userid);
//		System.out.println("비밀번호: " + userpwd);
//		System.out.println("이메일: " + email);
//		System.out.println("성별: " + gender);
//		System.out.println("스포츠: " + sports);
		System.out.println(joinDTO);
		return "member/joinProc";
	}
}
