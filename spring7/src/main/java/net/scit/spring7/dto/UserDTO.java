package net.scit.spring7.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring7.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UserDTO {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String roles;
	private Boolean enabled;
	
	public static UserDTO toDTO(UserEntity entity) {
		return UserDTO.builder()
				.userId(entity.getUserId())
				.userPwd(entity.getUserPwd())
				.userName(entity.getUserName())
				.email(entity.getEmail())
				.roles(entity.getRoles())
				.enabled(entity.getEnabled())
				.build();
	}
}
