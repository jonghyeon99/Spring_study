package net.scit.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.UserDTO;
import net.scit.carsharing.service.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    // 1) 회원가입 폼
    @GetMapping("/join")
    public String join() {
        return "/user/join";
    }

    // 2) 회원가입 처리
    @PostMapping("/joinProc")
    public String joinProc(@ModelAttribute UserDTO userDTO) {
        log.info("가입 정보 : {}", userDTO.toString());
        userService.joinProc(userDTO);
        // 가입 완료 후 로그인 페이지로
        return "redirect:/user/login";
    }

    // 3) 아이디 중복검사
    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam("userId") String userId) {
        boolean result = userService.idCheck(userId);
        return result;
    }


    // 4) 로그인 폼 (GET)
    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, Model model) {
        // 로그인 실패 시 ?error=true
        if (error != null) {
            model.addAttribute("errMessage", "아이디나 비밀번호가 틀렸습니다.");
        }
        return "/user/login";
    }
}
