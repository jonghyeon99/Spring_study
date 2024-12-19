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
	private String guestName;
	private String guestPwd;
	private String content;
	private LocalDate regdate;
	
	public static GuestbookDTO toDTO(GuestbookEntity guestbookEntity) {
		return GuestbookDTO.builder()
				.seqno(guestbookEntity.getSeqno())
				.guestName(guestbookEntity.getGuestName())
				.guestPwd(guestbookEntity.getGuestPwd())
				.content(guestbookEntity.getContent())
				.regdate(guestbookEntity.getRegdate())
				.build();
	}
}