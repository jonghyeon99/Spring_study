package net.scit.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.SharingUserDTO;
import net.scit.carsharing.service.SharingUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class SharingUserController {
	private final SharingUserService sharingUserService;

	/**
	 * 회원가입 화면 요청
	 * 
	 * @return
	 */
	@GetMapping("join")
	public String join() {
		return "/user/join";
	}

	/**
	 * 회원가입 처리 요청
	 * 
	 * @return
	 */
	@PostMapping("/joinProc")
	public String joinProc(@ModelAttribute SharingUserDTO sharingUserDTO) {
		sharingUserService.insert(sharingUserDTO);

		return "redirect:/";
	}

	/**
	 * 중복 아이디 확인
	 */
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name = "userId") String userId) {
		
		SharingUserDTO dto = sharingUserService.selectUser(userId);
		// userId에 해당하는 회원이 존재하지 않으면 회원가입을 할 수 있으므로 true
		if (dto == null)
			return true;

		return false;
	}
	
	/**
	 * 1) 로그인 화면 요청
	 * 2) 아이디나 비밀번호가 잘못 입력되었을 때 처리
	 * @param error
	 * @param errMessage
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(
			@RequestParam(value="error", required=false) boolean error 
			, @RequestParam(value="errMessage", required=false) String errMessage 
			, Model model
			) {

		model.addAttribute("error", error);
		model.addAttribute("errMessage", "아이디나 비밀번호가 틀렸습니다.");
		
		return "/user/login";
	}


}