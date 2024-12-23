package net.scit.spring5.entity;

import java.time.LocalDate;

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
import net.scit.spring5.dto.PhoneDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder	// @AllArgsConstructer가 있어야 한다.
@Entity
@Table(name="phonebook")	// 클래스명과 테이블명이 동일하면 생략가능
public class PhoneEntity {
	@Id						// PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")		// 멤버명과 테이블의 컬럼명이 동일하면 생략가능
	private Integer id;
	
	@Column(name="fname", nullable = false)
	private String fname;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="birthday")
	private LocalDate birthday;
	
	@Column(name="ftype")
	private Boolean ftype;
	
	// DTO를 Entity로 변환하는 static 메소드 toEntity()
	public static PhoneEntity toEntity(PhoneDTO phoneDTO) {
		return PhoneEntity.builder()
				.id(phoneDTO.getId())
				.fname(phoneDTO.getFname())
				.phone(phoneDTO.getPhone())
				.birthday(phoneDTO.getBirthday())
				.ftype(phoneDTO.getFtype())
				.build();
	}
}
