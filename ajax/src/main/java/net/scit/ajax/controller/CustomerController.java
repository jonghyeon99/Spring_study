package net.scit.ajax.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.scit.ajax.CustomerDTO;

@Controller
public class CustomerController {

	@GetMapping("/customer")
	public String customer() {
		return "customer";
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<CustomerDTO> customerList() {
		// DB에서 조회된 데이터라고 가정함
		List<CustomerDTO> list = Arrays.asList(
				new CustomerDTO(1, "홍길동", "010-1111-1212", "남성", "서울시 종로구"),
				new CustomerDTO(2, "이순신", "010-2222-1212", "남성", "서울시 송파구"),
				new CustomerDTO(3, "유관순", "010-3333-1212", "여성", "서울시 관악구"),
				new CustomerDTO(4, "신사임당", "010-4444-1212", "여성", "서울시 도봉구"),
				new CustomerDTO(5, "세종대왕", "010-5555-1212", "남성", "서울시 강동구")
				);
		return list;
	}
}
