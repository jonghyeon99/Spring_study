package net.scit.carsharing.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.MyOrderDTO;
import net.scit.carsharing.entity.SharingCarEntity;
import net.scit.carsharing.entity.SharingOrderEntity;
import net.scit.carsharing.entity.SharingUserEntity;
import net.scit.carsharing.repository.SharingCarRepository;
import net.scit.carsharing.repository.SharingOrderRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class SharingOrderService {

	private final SharingOrderRepository sharingOrderRepository;
	private final SharingCarRepository sharingCarRepository;

	/**
	 * 차량 예약
	 * @param userId
	 * @param carSeq
	 */
	@Transactional
	public void reservation(Integer carSeq, boolean carStatus, String loginId) {
		Optional<SharingCarEntity> temp  = sharingCarRepository.findById(carSeq);
		temp.get().setCarStatus(carStatus);           // Car 테이블의 상태값을 0으로 수정

		
		SharingOrderEntity entity 
		    = SharingOrderEntity.toEntity(new SharingUserEntity(), new SharingCarEntity(), carStatus);

		entity.getCarEntity().setCarSeq(carSeq);
		entity.getUserEntity().setUserId(loginId); 	// 로그인 ID 세팅
		entity.setSharingStatus(carStatus);

		sharingOrderRepository.save(entity); 		// 주문 내역 저장
	}

	/**
	 * 나의 차량 예약 정보
	 * @param userId 
	 * @return
	 */
	public List<MyOrderDTO> myReserveInfo(String userId) {
	    List<MyOrderDTO> list = sharingOrderRepository.findOrderCarInfoByUserId(userId);
//		List<Object[]> results = sharingOrderRepository.findOrderCarInfoByUserIdNative(userId);
//
//		List<MyOrderDTO> list = new ArrayList<>();
//		for(int i=0; i<results.size(); ++i) {
//			MyOrderDTO dto = new MyOrderDTO(
//					(Integer) results.get(i)[0], // orderSeq
//					(String) results.get(i)[1],  // userId
//					(Integer) results.get(i)[2], // carSeq
//					results.get(i)[3]+"",  // sharingStatus
//					((Date) results.get(i)[4]).toLocalDate().atStartOfDay(),   // sharingDate
//					(String) results.get(i)[5]);
//
//			list.add(dto);
//		}

		return list;

	}

	/**
	 * 차량 반납
	 * @param carSeq
	 * @param orderSeq 
	 * @param loginId
	 */
	@Transactional
	public void carReturn(Integer carSeq) {
		// Car 테이블의 상태값을 수정
		Optional<SharingCarEntity> temp1  = sharingCarRepository.findById(carSeq);
		temp1.get().setCarStatus(true); 
	}

	/**
	 * 
	 * @param orderSeq
	 */
	@Transactional
	public void updateOrder(Integer orderSeq) {
		// Order 테이블의 상태값을 1로 수정
		Optional<SharingOrderEntity> temp2  = sharingOrderRepository.findById(orderSeq);
		temp2.get().setSharingStatus(true); 
	}
}
