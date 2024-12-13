package net.scit.todoList.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.todoList.dto.TodoListDTO;
import net.scit.todoList.entity.TodoListEntity;
import net.scit.todoList.repository.TodoListRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoListService {

	private final TodoListRepository repository;
	
	public void insert(TodoListDTO todoListDTO) {
		log.info("----------{}", todoListDTO.toString());
		
		TodoListEntity todoListEntity = TodoListEntity.toEntity(todoListDTO);
		
		repository.save(todoListEntity);	// 전달인자로 반드시 entity 타입이 와야 함
	}
	
	public List<TodoListDTO> selectAll() {
		List<TodoListEntity> temp = repository.findAll(Sort.by(Sort.Direction.ASC, "seqno"));
		List<TodoListDTO> list = new ArrayList<>();
		
		for(TodoListEntity entity : temp) {
			TodoListDTO dto = TodoListDTO.toDTO(entity);
			
			list.add(dto);
		}
		return list;
	}
	
	public void delete(Integer seqno) {
		repository.deleteById(seqno);
		
	}
}
