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
import net.scit.carsharing.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userPw;
	private String userNm;
	private String roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(roles));
	}

	@Override
	public String getPassword() {
		return this.userPw;
	}

	@Override
	public String getUsername() {
		return this.userId;
	}

	public static LoginUserDetails toDTO(UserEntity entity)
	{
		return LoginUserDetails.builder()
							 .userId(entity.getUserId())
							 .userPw(entity.getUserPw())
							 .userNm(entity.getUserNm())
							 .roles(entity.getRoles())
							 .build();
	}
}
