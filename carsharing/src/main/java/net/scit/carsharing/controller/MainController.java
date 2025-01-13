package net.scit.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping({"/", ""})
    public String index() {
     
        return "index";
    }

/*    
    
	@GetMapping({"/", ""})
	public String index(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, Model model
			) {
		
		if(loginUser != null) {
			String loginName = loginUser.getUserNm();   // 실명
			String loginId   = loginUser.getUsername();	  // 아이디

			model.addAttribute("loginName", loginName);
			model.addAttribute("loginId", loginId);
		}
		
		return "index";
	}
	*/
}
