package net.scit.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BMIController {

	@GetMapping("/bmi")
	public String bmi(
			@RequestParam(name="height") double height
			, @RequestParam(name="weight") double weight
			) {
		// BMI 계산
		// bmi = 몸무게(kg) / 키(m)의 제곱
		// 키를 m로 환산하는 작업
		double temp = height * 0.01;
		double bmi = weight / (temp * temp);
		System.out.println("BMI : " + bmi);		// 15~30
		return "bmi_result";
	}
}
