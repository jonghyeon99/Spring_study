package net.scit.carsharing.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="sharing_order")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_seq")
	private Integer	orderSeq;
	
	@Column(name="sharing_status")
	private String	sharingStatus;
	
	@Column(name="sharing_date")
	@CurrentTimestamp
	private LocalDateTime sharingDate;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="car_id", referencedColumnName = "car_seq")
	private CarEntity car;
	
	@PrePersist
	private void setDefaultSharingStatus()
	{
		if (this.sharingStatus == null)
			this.sharingStatus = "0";
	}
	
	public static OrderEntity toEntity(UserEntity userEntity, CarEntity carEntity)
	{
		OrderEntity entity = OrderEntity.builder()
										 .orderSeq(null)
										 .sharingDate(null)
										 .user(userEntity)
										 .car(carEntity)
										 .build();
		
		entity.setDefaultSharingStatus();
		
		return entity;
	}
}
