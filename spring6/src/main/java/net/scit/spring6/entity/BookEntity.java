package net.scit.spring6.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
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
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.dto.ReadingNoteDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="book")
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_seq")
	private Integer bookSeq;
	
	@Column(name="title")
	private String title;
	
	@Column(name="writer")
	private String writer;
	
	@Column(name="publisher")
	private String publisher;
	
	@Column(name="purchase_date")
	private LocalDate purchaseDate;
	
	@Column(name="price")
	private Integer price;

	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private ReadingNoteEntity readingNote;
	
	public static BookEntity toEntity(BookDTO bookDTO) {
	    return BookEntity.builder()
	            .bookSeq(bookDTO.getBookSeq())
	            .title(bookDTO.getTitle())
	            .writer(bookDTO.getWriter())
	            .publisher(bookDTO.getPublisher())
	            .purchaseDate(bookDTO.getPurchaseDate())
	            .price(bookDTO.getPrice())
	            .build();
	}
}
