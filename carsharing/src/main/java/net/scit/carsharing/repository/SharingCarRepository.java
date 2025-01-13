package net.scit.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.carsharing.entity.SharingCarEntity;

public interface SharingCarRepository extends JpaRepository<SharingCarEntity, Integer>{

}
