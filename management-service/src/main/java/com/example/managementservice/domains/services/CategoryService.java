package com.example.managementservice.domains.services;

import com.example.managementservice.domains.entities.CategoryEntity;

public interface CategoryService {

    public CategoryEntity findById(Long id);
}
