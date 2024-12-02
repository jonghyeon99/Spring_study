package net.scit.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({"/" , ""})
	// http://localhost:9001/     http://localhost:9001
	public String index() {
		return "index";
	}
}
