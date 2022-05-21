package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.entities.CategoryEntity;
import com.example.managementservice.domains.exceptions.NotFoundException;
import com.example.managementservice.domains.repositories.CategoryRepository;
import com.example.managementservice.domains.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryEntity findById(Long id){
        return categoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    public List<CategoryEntity> findAllEntityByPromotionIn(List<Long> promotionIds){
        return categoryRepository.findCategoryEntitiesByPromotionIdIn(promotionIds);
    }
}
