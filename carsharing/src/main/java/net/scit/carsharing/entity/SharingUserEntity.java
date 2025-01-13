package net.scit.carsharing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.scit.carsharing.dto.SharingUserDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "sharing_user")
public class SharingUserEntity {

	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "user_pw")
	private String userPwd;

	@Column(name = "user_nm", nullable = false)
	private String userNm;

	@Column(name = "roles", length = 50)
	@Builder.Default
	private String roles = "ROLE_USER";

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
	private List<SharingOrderEntity> orders;

	public static SharingUserEntity toEntity(SharingUserDTO dto) {
		return SharingUserEntity.builder()
				.userId(dto.getUserId())
				.userPwd(dto.getUserPwd())
				.userNm(dto.getUserNm())
				.build();
	}

}
