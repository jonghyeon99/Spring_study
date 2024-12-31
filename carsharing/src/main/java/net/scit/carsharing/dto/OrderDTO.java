package net.scit.carsharing.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.entity.OrderEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderDTO {
	private Integer orderSeq;
	private String userId;
	private Boolean isMyOrder;
	private Integer carId;
	private String carType;
	private String sharingStatus;
	private LocalDateTime sharingDate;
	
	public static OrderDTO toDTO(OrderEntity entity, String myUserId) {
		return OrderDTO.builder()
					   .orderSeq(entity.getOrderSeq())
					   .userId(entity.getUser().getUserId())
					   .isMyOrder(entity.getUser().getUserId().equals(myUserId))
					   .carId(entity.getCar().getCarSeq())
					   .carType(entity.getCar().getCarType())
					   .sharingStatus(entity.getSharingStatus())
					   .sharingDate(entity.getSharingDate())
					   .build();
	}
}
