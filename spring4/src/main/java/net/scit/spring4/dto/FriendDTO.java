package net.scit.spring4.dto;

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
public class FriendDTO {
	
	private String name;
	private Integer age;
	private String phone;
	private LocalDate birthday;
	private boolean active;
}
