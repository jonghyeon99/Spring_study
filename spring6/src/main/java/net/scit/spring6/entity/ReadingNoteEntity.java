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
import net.scit.spring6.dto.ReadingNoteDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="reading_note")
public class ReadingNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reading_seq")
    private String readingSeq;

    @Column(name="read_status")
    private String readStatus;

    @Column(name="read_date")
    private LocalDate readDate;

    @Column(name="book_review")
    private String bookReview;

    @OneToOne
    @JoinColumn(name = "book_seq", referencedColumnName = "book_seq")
    private BookEntity book;

    public static ReadingNoteEntity toEntity(ReadingNoteDTO readingNoteDTO, BookEntity book) {
        return ReadingNoteEntity.builder()
                .readingSeq(readingNoteDTO.getReadingSeq())
                .readStatus(readingNoteDTO.getReadStatus())
                .readDate(readingNoteDTO.getReadDate())
                .bookReview(readingNoteDTO.getBookReview())
                .book(book) // BookEntity 설정
                .build();
    }
}
