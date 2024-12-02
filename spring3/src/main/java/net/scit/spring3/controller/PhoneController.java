package net.scit.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.scit.spring3.dto.PhoneDTO;

@Controller
@RequestMapping("/friend")
public class PhoneController {
	/*
	 * 전화번호 등록
	 */
	@GetMapping("/phoneView")
	public String phoneView() {
		return "friend/phoneView";
	}
	/*
	 * 전화번호 처리 요청
	 * VO --> DTO(Data Transfer Object)
	 */
	@PostMapping("/phoneProc")
	public String phoneProc(
//			@RequestParam(name="name") String name
//			, @RequestParam(name="phone") String phone
//			, @RequestParam(name="addr") String addr
//			, @RequestParam(name="type", defaultValue = "1") boolean type
			@ModelAttribute PhoneDTO phoneDTO
			) {
//		System.out.println("이름: " + name);
//		System.out.println("전화번호: " + phone);
//		System.out.println("주소: " + addr);
//		System.out.println("성격: " + type);
		System.out.println(phoneDTO);
		return "friend/phoneProc";
	}
}
