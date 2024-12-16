package net.scit.guestbook.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.guestbook.entity.GuestbookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GuestbookDTO {
	private Integer seqno;
	private String guest_name;
	private String guest_pwd;
	private String content;
	private LocalDate regdate;
	
	public static GuestbookDTO toDto(GuestbookEntity guestbookEntity) {
		return GuestbookDTO.builder()
				.seqno(guestbookEntity.getSeqno())
				.guest_name(guestbookEntity.getGuest_name())
				.guest_pwd(guestbookEntity.getGuest_pwd())
				.content(guestbookEntity.getContent())
				.regdate(guestbookEntity.getRegdate())
				.build();
	}
}