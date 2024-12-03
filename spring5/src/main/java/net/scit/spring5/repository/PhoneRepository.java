package net.scit.spring5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring5.entity.PhoneEntity;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {
	
}
