package net.scit.spring6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.scit.spring6.entity.ReadingNoteEntity;

@Repository
public interface ReadingRepository extends JpaRepository<ReadingNoteEntity, String> {

}
