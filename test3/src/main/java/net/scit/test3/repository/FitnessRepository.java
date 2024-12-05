package net.scit.test3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.test3.entity.FitnessEntity;

public interface FitnessRepository extends JpaRepository<FitnessEntity, Integer>{

}
