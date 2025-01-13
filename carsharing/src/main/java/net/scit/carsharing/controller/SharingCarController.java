package net.scit.carsharing.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.LoginUserDetails;
import net.scit.carsharing.dto.SharingCarDTO;
import net.scit.carsharing.dto.SharingOrderDTO;
import net.scit.carsharing.service.SharingCarService;
import net.scit.carsharing.service.SharingOrderService;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
@Slf4j
public class SharingCarController {
	private final SharingCarService sharingCarService;

	/**
	 * 차량 목록 요청
	 * 
	 * @return
	 */
	@GetMapping("/carlist")
	public String carlist(Model model) {
		List<SharingCarDTO> list = sharingCarService.selectAll();
		
		model.addAttribute("list", list);
		return "/car/carlist";
	}
}