package com.example.managementservice.domains.services;

import com.example.managementservice.domains.dtos.CategoryProductPromotionDTO;

import java.util.List;

public interface CategoryProductService {

    public List<CategoryProductPromotionDTO> getPromotionalProducts();
}
