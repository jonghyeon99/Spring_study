package net.scit.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.entity.BookEntity;
import net.scit.spring6.repository.BookRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

	private final BookRepository repository;
	
	public void insert(BookDTO bookDTO) {
		log.info("----------{}", bookDTO.toString());
		
		BookEntity bookEntity = BookEntity.toEntity(bookDTO);
		
		repository.save(bookEntity);	// 전달인자로 반드시 entity 타입이 와야 함
	}
	
	public List<BookDTO> selectAll() {
		List<BookEntity> temp = repository.findAll(Sort.by(Sort.Direction.ASC, "bookSeq"));
		
		List<BookDTO> list = new ArrayList<>();
		
		temp.forEach((entity) -> list.add(BookDTO.toDTO(entity)));
		return list;
	}
	
	
	public void delete(Integer bookSeq) {
		repository.deleteById(bookSeq);
		
	}
}
