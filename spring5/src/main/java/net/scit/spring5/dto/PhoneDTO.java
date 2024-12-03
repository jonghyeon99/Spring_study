package net.scit.spring5.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring5.entity.PhoneEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder		// @AllArgsConstructor가 있어야 함
@Getter
@Setter
@ToString
public class PhoneDTO {

	private Integer id;
	private String fname;
	private String phone;
	private LocalDate birthday;
	private Boolean ftype;
	
	// Entity를ㄹ 받아서 DTO로 반환
	public static PhoneDTO toDTO(PhoneEntity phoneEntity) {
		return  PhoneDTO.builder()
				.id(phoneEntity.getId())
				.fname(phoneEntity.getFname())
				.phone(phoneEntity.getPhone())
				.birthday(phoneEntity.getBirthday())
				.ftype(phoneEntity.getFtype())
				.build();
	}
}
