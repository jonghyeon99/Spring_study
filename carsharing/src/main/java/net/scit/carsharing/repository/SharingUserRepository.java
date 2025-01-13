package net.scit.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.carsharing.entity.SharingUserEntity;

public interface SharingUserRepository extends JpaRepository<SharingUserEntity, String> {

}
