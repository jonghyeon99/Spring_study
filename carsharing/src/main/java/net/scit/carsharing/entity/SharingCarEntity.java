package net.scit.carsharing.entity;

/* Car(부모) 엔티티는 Order(자식) 엔티티와 1:1 관계
 * Order(자식)에서만 @OneToOne   */

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.dto.SharingCarDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "sharing_car")
public class SharingCarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_seq", nullable = false)
	private Integer carSeq;

	@Column(name = "car_type", nullable = false)
	private String carType;

	@Column(name = "car_status")
	private boolean carStatus;

	@OneToOne(mappedBy = "carEntity", cascade = CascadeType.ALL)
	private SharingOrderEntity orderEntity;

	public static SharingCarEntity toEntity (SharingCarDTO sharingCarDTO) { 
		return SharingCarEntity.builder()
				.carSeq(sharingCarDTO.getCarSeq())
				.carType(sharingCarDTO.getCarType())
				.carStatus(sharingCarDTO.isCarStatus())
				.build();
	} 
}

