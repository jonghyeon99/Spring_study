package net.scit.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/display")
public class ThymeleafController {

	@GetMapping("/text")
	public String text(Model model) {
		String korean = "대한민국!!! ❤️❤️❤️❤️";
		String english = "I have a dream!";
		String japanese = "こんにちは";
	 	String tag = "<marquee>돌이 굴러가유</marquee>";
	 	String url = "https://www.naver.com";
	 	
	 	// 숫자
	 	int age = 25;
	 	double pi = Math.PI;
	 	
	 	// 빈 데이터
	 	String nullData = null;
	 	String emptyData = "";
	 	
//	 	nullData.charAt(1);	// NullPointerException
//	 	emptyData.charAt(1); // IndexOutOfBoundsException
	 	
	 	model.addAttribute("kor", korean);
	 	model.addAttribute("eng", english);
	 	model.addAttribute("jpn",  japanese);
	 	model.addAttribute("tag", tag);
	 	model.addAttribute("url", url);
	 	model.addAttribute("age", age);
	 	model.addAttribute("pi", pi);
	 	model.addAttribute("nullData", nullData);
	 	model.addAttribute("emptyData", emptyData);
	 	
	 	return "display/text"; 
	}
}
