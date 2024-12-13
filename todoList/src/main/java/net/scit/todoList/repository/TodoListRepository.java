package net.scit.todoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.todoList.entity.TodoListEntity;

public interface TodoListRepository extends JpaRepository<TodoListEntity, Integer> {

}
