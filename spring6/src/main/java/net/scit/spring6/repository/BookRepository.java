package net.scit.spring6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.scit.spring6.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer>{

}
