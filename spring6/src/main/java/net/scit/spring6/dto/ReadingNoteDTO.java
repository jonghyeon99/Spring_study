package net.scit.spring6.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.entity.BookEntity;
import net.scit.spring6.entity.ReadingNoteEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReadingNoteDTO {

	private String readingSeq;
	private String readStatus;
	private LocalDate readDate;
	private String bookReview;
	private Integer bookSeq;
    private String book;
    
    public static ReadingNoteDTO toDTO(ReadingNoteEntity entity) {
        return ReadingNoteDTO.builder()
                .readingSeq(entity.getReadingSeq())
                .readStatus(entity.getReadStatus())
                .readDate(entity.getReadDate())
                .bookReview(entity.getBookReview())
                .bookSeq(entity.getBook().getBookSeq()) // BookEntity의 bookSeq를 가져옴
                .build();
    }
}
