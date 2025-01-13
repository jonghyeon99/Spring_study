package net.scit.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.entity.SharingCarEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class SharingCarDTO {
    private Integer carSeq;
    private String carType;
    private boolean carStatus;
    
	public static SharingCarDTO toDTO(SharingCarEntity sharingCarEntity) { 
		return SharingCarDTO.builder()
				.carSeq(sharingCarEntity.getCarSeq())
				.carType(sharingCarEntity.getCarType())
				.carStatus(sharingCarEntity.isCarStatus())
				.build();
	} 
}
