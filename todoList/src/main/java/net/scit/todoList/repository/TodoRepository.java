package net.scit.todoList.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.todoList.entity.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

	List<TodoEntity> findByImportanceAndCategories(String importance, String categories, Sort sort);

}
