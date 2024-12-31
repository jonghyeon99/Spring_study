package net.scit.spring7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring7.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
