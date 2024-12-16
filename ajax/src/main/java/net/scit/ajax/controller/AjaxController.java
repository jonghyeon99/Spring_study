package net.scit.ajax.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxController {

	@GetMapping("ajaxReq1")
	public String ajaxReq1() {
		
		return "Received Success!!!";
	}
	
	@PostMapping("/ajaxReq2")
	public Map<String, String> ajaxReq2(
			@RequestParam(name="name") String name,
			@RequestParam(name="phone") String phone
			) {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("phone", phone);
		
		return map;
	}
	
	@GetMapping("ajaxReq3")
	public List<String> ajaxReq3() {
		List<String> list = Arrays.asList("관악구", "종로구", "영등포구", "강서구", "성북구");
		return list;
	}
}
