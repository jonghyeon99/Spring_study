package net.scit.spring6.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring6.dto.BookDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="book")
public class BookEntity {
	@Id
	@Column(name="book_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookSeq;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "writer", nullable = false)
	private String writer;

	@Column(name = "publisher", nullable = false)
	private String publisher;

	@Column(name="purchase_date")
	private LocalDate purchaseDate;
	
	@Column(name="price")
	private Integer price;

//    @OneToOne(mappedBy = "bookEntity")
//    private ReadingNoteEntity readingNoteEntity;
    
	public static BookEntity toEntity(BookDTO bookDTO) {
		BookEntity entity = BookEntity.builder()
				.bookSeq(bookDTO.getBookSeq())
				.title(bookDTO.getTitle())
				.writer(bookDTO.getWriter())
				.publisher(bookDTO.getPublisher())
				.purchaseDate(bookDTO.getPurchaseDate())
				.price(bookDTO.getPrice())
				.build();
		return entity;
	}
}
