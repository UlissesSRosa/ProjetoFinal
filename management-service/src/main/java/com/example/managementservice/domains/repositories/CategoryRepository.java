package com.example.managementservice.domains.repositories;

import com.example.managementservice.domains.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findCategoryEntitiesByPromotionIdIn(List<Long> promotionIds);

    Page<CategoryEntity> findAll(Pageable pageable);
}
