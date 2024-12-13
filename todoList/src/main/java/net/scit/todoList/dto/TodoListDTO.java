package net.scit.todoList.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.todoList.entity.TodoListEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder		// @AllArgsConstructor가 있어야 함
@Getter
@Setter
@ToString
public class TodoListDTO {

	private Integer seqno;
	private LocalDate regdate;
	private String status;
	private String importance;
	private String categories;
	private String todo;
	
	// Entity를 받아서 DTO로 반환
	public static TodoListDTO toDTO(TodoListEntity todoListEntity) {
		return  TodoListDTO.builder()
				.seqno(todoListEntity.getSeqno())
				.regdate(todoListEntity.getRegdate())
				.status(todoListEntity.getStatus())
				.importance(todoListEntity.getImportance())
				.categories(todoListEntity.getCategories())
				.todo(todoListEntity.getTodo())
				.build();
	}
}
