package net.scit.carsharing.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.dto.CarDTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "SHARING_CAR")
public class CarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAR_SEQ")
	private Integer carSeq;
	
	@Column(name = "CAR_TYPE", nullable = false)
	private String carType;
	
	@Column(name = "CAR_STATUS")
	private String carStatus;
	
	@PrePersist
    public void prePersist() {
        if (this.carStatus == null) {
            this.carStatus = "0"; // 기본값 설정
        }
    }
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders; // Car ↔ Order 관계 (1:N)
	
	public static CarEntity toEntity(CarDTO carDTO) {
		return CarEntity.builder()
				.carSeq(carDTO.getCarSeq())
				.carType(carDTO.getCarType())
				.carStatus(carDTO.getCarStatus())
				.build();
	}
}
