package net.scit.todoList.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.todoList.dto.TodoListDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="todolist")
public class TodoListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seqno")
	private Integer seqno;
	
	@Column(name="regdate")
	private LocalDate regdate;

	@Column(name="status")
	private String status;
	
	@Column(name="importance")
	private String importance;
	
	@Column(name="categories")
	private String categories;
	
	@Column(name="todo")
	private String todo;
	
	// DTO를 Entity로 변환하는 static 메소드 toEntity()
	public static TodoListEntity toEntity(TodoListDTO todoListDTO) {

		return TodoListEntity.builder()
				.seqno(todoListDTO.getSeqno())
				.regdate(todoListDTO.getRegdate())
				.status(todoListDTO.getStatus())
				.importance(todoListDTO.getImportance())
				.categories(todoListDTO.getCategories())
				.todo(todoListDTO.getTodo())
				.build();
	}
}
