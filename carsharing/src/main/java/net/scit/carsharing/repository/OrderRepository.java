package net.scit.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.carsharing.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
