package com.example.managementservice.domains.repositories;

import com.example.managementservice.domains.entities.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRespository extends JpaRepository<ShoppingCartEntity, Long> {
}
