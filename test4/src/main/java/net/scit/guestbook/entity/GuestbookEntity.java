package net.scit.guestbook.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.guestbook.dto.GuestbookDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="guestbook")
public class GuestbookEntity {
	
	@Id
	@Column(name="seqno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seqno;
	
	@Column(name="guest_name")
	private String guestName;
	
	@Column(name="guest_pwd")
	private String guestPwd;
	
	@Column(name="content")
	private String content;
	
	@Column(name="regdate")
	@CreationTimestamp
	private LocalDate regdate;
	
	public static GuestbookEntity toEntity(GuestbookDTO guestbookDTO) {
		return GuestbookEntity.builder()
				.seqno(guestbookDTO.getSeqno())
				.guestName(guestbookDTO.getGuestName())
				.guestPwd(guestbookDTO.getGuestPwd())
				.content(guestbookDTO.getContent())
				.regdate(guestbookDTO.getRegdate())
				.build();
				
	}
}
