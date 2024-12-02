package net.scit.spring5.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PhoneDTO {

	private Integer id;
	private String fname;
	private String phone;
	private LocalDate birthday;
	private Boolean ftype;
}
