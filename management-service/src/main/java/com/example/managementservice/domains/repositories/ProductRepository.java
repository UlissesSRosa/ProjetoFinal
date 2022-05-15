package com.example.managementservice.domains.repositories;

import com.example.managementservice.domains.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByNameLike(String name);

    Page<ProductEntity> findAllPageable(Pageable pageable);
}
