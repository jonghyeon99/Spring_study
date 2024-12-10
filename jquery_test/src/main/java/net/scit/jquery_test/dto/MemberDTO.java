package net.scit.jquery_test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {
	private String userid;
	private String email;
	private Integer age;
	private String location;
}
