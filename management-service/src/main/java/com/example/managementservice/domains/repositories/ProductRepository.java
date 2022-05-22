package com.example.managementservice.domains.repositories;

import com.example.managementservice.domains.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByNameContains(String name);

    Page<ProductEntity> findAll(Pageable pageable);

    @Query("select p from ProductEntity p where p.categoryId.id in (:categoryIds)")
    List<ProductEntity> findProductEntitiesByCategoryIdIn(List<Long> categoryIds);
}
