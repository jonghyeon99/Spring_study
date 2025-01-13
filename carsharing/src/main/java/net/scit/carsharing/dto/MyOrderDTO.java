// 내가 예약한 정보를 출력하기 위한 DTO 파일
// 
package net.scit.carsharing.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MyOrderDTO {
	private Integer orderSeq;			// 주문 번호
	private String userId;				// 유저 아이디
	private Integer carSeq;				// 차량 번호
	private String sharingStatus;		// 반납여부
	private LocalDateTime sharingDate;	// 예약일자
	private String carType;				// 차종
	
}
