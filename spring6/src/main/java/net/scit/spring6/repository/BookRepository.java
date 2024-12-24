package net.scit.spring6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring6.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}
