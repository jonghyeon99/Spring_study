package net.scit.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.carsharing.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {

}
