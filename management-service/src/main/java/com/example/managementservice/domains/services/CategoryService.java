package com.example.managementservice.domains.services;

import com.example.managementservice.domains.dtos.CategoryDTO;
import com.example.managementservice.domains.dtos.requests.CategoryRequestDTO;
import com.example.managementservice.domains.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CategoryService {

    public CategoryEntity findById(Long id);

    public Page<CategoryDTO> findAllPageable(Pageable pageable);

    public List<CategoryEntity> findAllEntityByPromotionIn(List<Long> promotionIds);

    public CategoryDTO create(CategoryRequestDTO categoryRequestDTO);

    public void delete(Long id);
}
