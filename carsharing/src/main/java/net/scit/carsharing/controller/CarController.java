package net.scit.carsharing.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.CarDTO;
import net.scit.carsharing.dto.LoginUserDetails;
import net.scit.carsharing.dto.OrderDTO;
import net.scit.carsharing.service.CarService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {
	private final CarService carService;
	
	@GetMapping("/carList")
	public String carList() {
		return "/car/carList";
	}
	
	@GetMapping("/showCarList")
	@ResponseBody
	public List<CarDTO> showCarList() {
		return carService.selectCarAll();
	}
	
	@PostMapping("/reserveCar")
	@ResponseBody
	public String postReserveCar(
				@AuthenticationPrincipal LoginUserDetails loginUserDetails
				, @RequestParam(name="carSeq") Integer carSeq
			) {
		String returnPage;
		if (carService.reserveCar(loginUserDetails.getUserId(), carSeq)) {
			// 예약 성공
			returnPage = "/car/myReservation";
		} else {
			// 예약 실패
			returnPage = "/car/carList";
		}

		return returnPage;
	}

	@GetMapping("/myReservation")
	public String getMyReservation() {
		return "car/myReservation";
	}
	
	@GetMapping("/showMyCarList")
	@ResponseBody
	public List<OrderDTO> getShowMyCarList() {
		return carService.selectOrderAll();
	}
	
	@PostMapping("/returnCar")
	@ResponseBody
	public String postReturnCar(
				@AuthenticationPrincipal LoginUserDetails loginUserDetails
				, @RequestParam(name="orderSeq") Integer orderSeq
			) {
		String returnPage;
		if (carService.returnCar(orderSeq)) {
			returnPage = "/car/carList";
		} else {
			returnPage = "/car/myReservation";
		}

		return returnPage;
	}
}
