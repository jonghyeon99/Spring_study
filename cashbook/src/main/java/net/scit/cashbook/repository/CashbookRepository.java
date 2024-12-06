package net.scit.cashbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.cashbook.entity.CashbookEntity;

public interface CashbookRepository extends JpaRepository<CashbookEntity, Long> {

}
