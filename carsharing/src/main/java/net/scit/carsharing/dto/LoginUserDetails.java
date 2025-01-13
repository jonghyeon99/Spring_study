package net.scit.carsharing.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class LoginUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userPwd;
	private String userNm;
	private String roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(roles));
	}

	@Override
	public String getPassword() {
		return this.userPwd;
	}

	@Override
	public String getUsername() {

		return this.userId;
	}

	// 사용자 정의 getter(뷰단에서 써볼라고!!)
	public String getUserNm() {
		return this.userNm;
	}

	// entity --> LoginUserDetails
	public static LoginUserDetails toDTO(SharingUserEntity userEntity) {
		return LoginUserDetails.builder()
				.userId(userEntity.getUserId())
				.userPwd(userEntity.getUserPwd())
				.userNm(userEntity.getUserNm())
				.roles(userEntity.getRoles())
				.build();
	}
}
