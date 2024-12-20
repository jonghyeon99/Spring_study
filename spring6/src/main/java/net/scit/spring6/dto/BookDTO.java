package net.scit.spring6.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.entity.BookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookDTO {
	
	private Integer bookSeq;
	private String title;
	private String writer;
	private String publisher;
	private LocalDate purchaseDate;
	private Integer price;
    private String readingNote;
    
    public static BookDTO toDTO(BookEntity entity) {
		return BookDTO.builder()
				.bookSeq(entity.getBookSeq())
				.title(entity.getTitle())
				.writer(entity.getWriter())
				.publisher(entity.getPublisher())
				.purchaseDate(entity.getPurchaseDate())
				.price(entity.getPrice())
				.build();
	}
}
