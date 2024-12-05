package net.scit.test3.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.test3.dto.FitnessDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="fitness")
public class FitnessEntity {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="fname")
	private String fname;
	
	@Column(name="gender")
	private Boolean gender;
	
	@Column(name="height")
	private double height;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="joinDate")
	@CreationTimestamp
	private LocalDate joinDate;
	
	
	// DTO를 Entity로 변환하는 static 메소드 toEntity()
	public static FitnessEntity toEntity(FitnessDTO fitnessDTO) {
		return FitnessEntity.builder()
				.id(fitnessDTO.getId())
				.fname(fitnessDTO.getFname())
				.gender(fitnessDTO.getGender())
				.weight(fitnessDTO.getWeight())
				.height(fitnessDTO.getHeight())
				.joinDate(fitnessDTO.getJoinDate())
				.build();
	}
}
