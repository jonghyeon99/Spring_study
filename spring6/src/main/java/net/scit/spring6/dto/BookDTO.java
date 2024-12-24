package net.scit.spring6.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.entity.BookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Builder
public class BookDTO {
	public Integer bookSeq;
	private String title;
	private String writer;
	private String publisher;
	private LocalDate purchaseDate;
	public Integer price;
	
	public static BookDTO toDTO(BookEntity entity) {
		BookDTO dto = BookDTO.builder()
				.bookSeq(entity.getBookSeq())
				.title(entity.getTitle())
				.writer(entity.getWriter())
				.publisher(entity.getPublisher())
				.purchaseDate(entity.getPurchaseDate())
				.price(entity.getPrice())
				.build();
			return dto;
	}
	
}







