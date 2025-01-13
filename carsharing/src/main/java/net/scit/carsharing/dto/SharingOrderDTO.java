package net.scit.carsharing.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.entity.SharingOrderEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class SharingOrderDTO {
    private Integer orderSeq;
    private String  userId;
    private Integer carSeq;
    private String carType;
    private boolean sharingStatus;
    private LocalDateTime sharingDate;
    
	public static SharingOrderDTO toDTO(SharingOrderEntity entity) {
		return SharingOrderDTO.builder()
				.orderSeq(entity.getOrderSeq())
				.userId(entity.getUserEntity().getUserId())
				.carSeq(entity.getCarEntity().getCarSeq())
				.carType(entity.getCarEntity().getCarType())
				.sharingStatus(entity.isSharingStatus())
				.sharingDate(entity.getSharingDate())
				.build();
		
	}
}
