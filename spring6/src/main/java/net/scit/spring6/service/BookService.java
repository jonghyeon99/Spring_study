package net.scit.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.entity.BookEntity;
import net.scit.spring6.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;
	/**
	 * 구매한 책 등록
	 * @param bookDTO
	 */
	public void insert(BookDTO bookDTO) {
		BookEntity entity = BookEntity.toEntity(bookDTO);
		bookRepository.save(entity);
	}
	/**
	 * 구매한 모든 도서 목록 조회 (구매일 기준 역순)
	 * @return
	 */
	public List<BookDTO> selectAll() {
		List<BookEntity> temp = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "purchaseDate"));
		List<BookDTO> list = new ArrayList<>(); 
		
		temp.forEach((entity) -> list.add(BookDTO.toDTO(entity)));
		
		return list;
	}

}
