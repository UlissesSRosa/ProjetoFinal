package com.example.orderservice.domains.repositories;

import com.example.orderservice.domains.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> getOrderEntitiesByUserId(Long userId);

}
