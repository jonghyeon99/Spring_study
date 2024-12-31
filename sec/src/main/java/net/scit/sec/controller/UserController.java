package net.scit.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	/**
	 * 회원가입을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	/**
	 * 회원가입을 위한 처리 요청
	 * @return
	 */	
	@PostMapping("/joinProc")
	public String join(@ModelAttribute UserDTO userDTO) {
		log.info("=== {}", userDTO.toString() );
		
		userService.joinProc(userDTO);
		return "redirect:/";
	}
	
	/**
	 * 아이디 중복확인 체크
	 * @param userId
	 * @return
	 */
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="userId") String userId) {
		boolean result = userService.idCheck(userId);
		return result;
	}
	
	/**
	 * 1) 로그인 화면 요청
	 * 2) 로그인 후 아이디나 비밀번호가 잘못되었을 때에도 이 요청 
	 * @return
	 */
	@GetMapping("/login")
	public String login(
			@RequestParam(value="error", required=false) String error
			, Model model
			) {	
		
		if(error != null) {
			model.addAttribute("error", error);
			model.addAttribute("errMessage", "아이디나 비밀번호가 틀렸습니다.");
		}
		
		return "login";
	}
	
	/**
	 * 로그인을 안하고 요청을 하면 login페이지로 리다이렉팅되는지 확인
	 * @return
	 */
	@GetMapping("/newsfeed")
	public String newsfeed() {
		return "newsfeed";
	}
	
	
	
	
	
	
	
}
