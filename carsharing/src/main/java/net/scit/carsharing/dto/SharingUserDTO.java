package net.scit.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.entity.SharingUserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class SharingUserDTO {
	private String userId;
	private String userPwd;
	private String userNm;
	private String roles;

	public static SharingUserDTO toDTO(SharingUserEntity sharingUserEntity) {
		return SharingUserDTO.builder()
				.userId(sharingUserEntity.getUserId())
				.userPwd(sharingUserEntity.getUserPwd())
				.userNm(sharingUserEntity.getUserNm())
				.build();
	}
}
