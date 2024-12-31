package net.scit.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.carsharing.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

	boolean existsByUserId(String userId);

	UserEntity findByUserId(String userId);
}
