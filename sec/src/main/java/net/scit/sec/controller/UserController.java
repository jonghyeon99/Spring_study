package net.scit.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.sec.dto.UserDTO;
import net.scit.sec.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	/*
	 * 회원가입을 위한 화면 요청
	 */
	@GetMapping("/join")
	public String join() {
		
		return "join";
	}
	
	/*
	 * 회원가입을 위한 처리 요청
	 */
	@PostMapping("/joinProc")
	public String join(@ModelAttribute UserDTO userDTO) {
		log.info("==== {}", userDTO.toString());
		userService.joinProc(userDTO);
		return "redirect:/";
	}
	
	/*
	 * 아이디 중복확인 체크
	 */
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="userId") String userId) {
		boolean result = userService.idCheck(userId);
		System.out.println("=============" + result);
		return result;
	}
	
	// login 요청은 일반 Controller에서 하면 안 됨!!!
	// Security가 처리하기 때문에!!!
}
