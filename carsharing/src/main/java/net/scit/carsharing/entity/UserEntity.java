package net.scit.carsharing.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.dto.UserDTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "SHARING_USER")
public class UserEntity {
	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "USER_PW", nullable = false)
	private String userPw;
	
	@Column(name = "USER_NM", nullable = false)
	private String userNm;
	
	@Column(name = "ROLES")
	private String roles;
	
	@PrePersist
    public void prePersist() {
        if (this.roles == null) {
            this.roles = "ROLE_USER"; // 기본값 설정
        }
    }
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders; // User ↔ Order 관계 (1:N)
	
	public static UserEntity toEntity(UserDTO userDTO) {
		return UserEntity.builder()
				.userId(userDTO.getUserId())
				.userPw(userDTO.getUserPw())
				.userNm(userDTO.getUserNm())
				.roles(userDTO.getRoles())
				.build();
	}
}
