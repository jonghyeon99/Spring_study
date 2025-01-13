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
import net.scit.carsharing.dto.MyOrderDTO;
import net.scit.carsharing.dto.SharingOrderDTO;
import net.scit.carsharing.service.SharingCarService;
import net.scit.carsharing.service.SharingOrderService;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class SharingOrderController {
	private final SharingOrderService sharingOrderService;

	/**
	 * 차량 예약 요청
	 * @param shareingOrderDTO
	 * @param loginUser
	 * @return
	 */

	@GetMapping("/reservation")
	public String  reservation(
			@RequestParam(name="carSeq") Integer carSeq
			, @RequestParam(name="carStatus") boolean carStatus
			, @AuthenticationPrincipal LoginUserDetails loginUser) {


		String loginId = loginUser.getUserId();
		log.info("예약 차량번호: {}", carSeq);
		log.info("대여가능 여부: {}", carStatus);
		log.info("로그인 유저: {}", loginId);


		sharingOrderService.reservation(carSeq, carStatus, loginId);

		return "redirect:/car/carlist";
	}

	/**
	 * 내 예약 / 반납 정보
	 * @param shareingOrderDTO
	 * @param loginUser
	 * @return
	 */

	@GetMapping("/myreserveinfo")
	public String  myreserveinfo(
			@AuthenticationPrincipal LoginUserDetails loginUser
			, Model model) {
		String userId = loginUser.getUserId();

		List<MyOrderDTO> list = sharingOrderService.myReserveInfo(userId);

		model.addAttribute("list", list);
		return "/car/myreserveinfo";
	}

	/**
	 * 차량 반납 요청
	 * @param shareingOrderDTO
	 * @param loginUser
	 * @return
	 */
	@GetMapping("/carReturn")
	public String  carReturn(
			@RequestParam(name="carSeq") Integer carSeq
			, @RequestParam(name="orderSeq") Integer orderSeq
			, @AuthenticationPrincipal LoginUserDetails loginUser) {
		
		sharingOrderService.carReturn(carSeq);
		sharingOrderService.updateOrder(orderSeq);
		
		return "redirect:/car/carlist";
	}
}