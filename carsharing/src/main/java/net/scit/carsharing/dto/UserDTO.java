package net.scit.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
	private String userId;
	private String userPw;
	private String userNm;
	private String roles;
	
	public static UserDTO toDTO(UserEntity userEntity) {
		return UserDTO.builder()
				.userId(userEntity.getUserId())
				.userPw(userEntity.getUserPw())
				.userNm(userEntity.getUserNm())
				.roles(userEntity.getRoles())
				.build();
	}
}
