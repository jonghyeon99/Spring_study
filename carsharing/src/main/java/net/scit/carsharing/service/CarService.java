package net.scit.carsharing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.CarDTO;
import net.scit.carsharing.dto.OrderDTO;
import net.scit.carsharing.entity.CarEntity;
import net.scit.carsharing.entity.OrderEntity;
import net.scit.carsharing.entity.UserEntity;
import net.scit.carsharing.repository.CarRepository;
import net.scit.carsharing.repository.OrderRepository;
import net.scit.carsharing.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {
	private final UserRepository userRepository;
	private final CarRepository carRepository;
	private final OrderRepository orderRepository;

	// 차량목록 페이지 진입 시 뿌려줄 데이터 가져오기
	public List<CarDTO> selectCarAll() {
		List<CarEntity> entityList = carRepository.findAll();
		List<CarDTO> dtoList = new ArrayList<>();
		
		entityList.forEach((entity) -> dtoList.add(CarDTO.toDTO(entity)));
		
		return dtoList;
	}

	public boolean reserveCar(String userId, Integer carSeq) {
		Optional<UserEntity> userEntityOp = userRepository.findById(userId);
		Optional<CarEntity> carEntityOp = carRepository.findById(carSeq);
		
		if (carEntityOp.isPresent() && userEntityOp.isPresent()){
			CarEntity carEntity = carEntityOp.get();
			UserEntity userEntity = userEntityOp.get();
			
			// Order 추가
			OrderEntity newOrderEntity = OrderEntity.toEntity(userEntity, carEntity);
			OrderEntity savedOrderEntity = orderRepository.save(newOrderEntity);
			
			// DB에 신규 Order 저장 성공여부 판단
			boolean isRegisterSucceeded = savedOrderEntity != null && savedOrderEntity.getOrderSeq() != null;
			
			// 차량 상태 변경
			carEntity.setCarStatus("1");
			CarEntity updatedCarEntity = carRepository.save(carEntity);
			
			// 상태 수정 성공여부 판단
			boolean isCarStatusUpdateSucceeded = updatedCarEntity != null && updatedCarEntity.getCarStatus() == "1";
			
			return isRegisterSucceeded && isCarStatusUpdateSucceeded;
		} else {
			// 없는 차량을 예약하려고 하므로 실패. false return
			return false;
		}
	}

	public List<OrderDTO> selectOrderAll() {
		List<OrderEntity> entityList = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "sharingDate"));
		List<OrderDTO> dtoList = new ArrayList<>();
		
		entityList.forEach((entity) -> dtoList.add(OrderDTO.toDTO(entity, getLoggedInUserId())));
		
		return dtoList;
	}

	public boolean returnCar(Integer orderSeq) {
		Optional<OrderEntity> orderEntityOp = orderRepository.findById(orderSeq);
		
		if (orderEntityOp.isPresent()) {	
			OrderEntity updateTarget_Order 	= orderEntityOp.get();
			CarEntity updateTarget_Car 		= updateTarget_Order.getCar();
			
			// Order 상태 반납완료로 변경
			updateTarget_Order.setSharingStatus("1");
			OrderEntity updatedOrderEntity = orderRepository.save(updateTarget_Order);
			
			// 상태 수정 성공여부 판단
			boolean isOrderStatusUpdateSucceeded = updatedOrderEntity != null && updatedOrderEntity.getSharingStatus() == "1";
			
			// 차량 상태 예약가능으로 변경
			updateTarget_Car.setCarStatus("0");
			CarEntity updatedCarEntity = carRepository.save(updateTarget_Car);
			
			// 상태 수정 성공여부 판단
			boolean isCarStatusUpdateSucceeded = updatedCarEntity != null && updatedCarEntity.getCarStatus() == "0";
			
			return isOrderStatusUpdateSucceeded && isCarStatusUpdateSucceeded;
		} else {
			// 없는 주문건을 수정하려 하므로 실패. false return
			return false;
		}
	}
	
	public String getLoggedInUserId() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        return authentication.getName(); // 기본적으로 사용자 이름 (ID)
	    }
	    return null; // 인증되지 않은 경우
	}
}