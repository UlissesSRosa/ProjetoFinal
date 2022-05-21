package com.example.managementservice.domains.services;

import com.example.managementservice.domains.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    public CategoryEntity findById(Long id);

    public List<CategoryEntity> findAllEntityByPromotionIn(List<Long> promotionIds);
}
