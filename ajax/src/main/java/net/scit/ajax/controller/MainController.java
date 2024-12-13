package net.scit.ajax.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping({"/", ""})
	@ResponseBody
	public Map<String, String> index() {
		List<String> list = Arrays.asList("사과", "딸기", "포도", "바나나");
		
		Map<String, String> map = new HashMap<>();
		
		map.put("a01", "홍길동");
		map.put("a02", "임꺽정");
		map.put("a03", "전우치");
		return map;
	}
	
	@GetMapping({"/data"})
	@ResponseBody
	public List<String> test() {
		List<String> list = Arrays.asList("사과", "딸기", "포도", "바나나");
		
		
		return list;
	}
}
