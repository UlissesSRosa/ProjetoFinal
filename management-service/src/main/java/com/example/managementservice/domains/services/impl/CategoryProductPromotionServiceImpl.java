package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.dtos.CategoryProductPromotionDTO;
import com.example.managementservice.domains.dtos.ProductDTO;
import com.example.managementservice.domains.entities.CategoryEntity;
import com.example.managementservice.domains.entities.PromotionEntity;
import com.example.managementservice.domains.services.CategoryProductService;
import com.example.managementservice.domains.services.CategoryService;
import com.example.managementservice.domains.services.ProductService;
import com.example.managementservice.domains.services.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryProductPromotionServiceImpl implements CategoryProductService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final PromotionService promotionService;


    public List<CategoryProductPromotionDTO> getPromotionalProducts() {
        List<PromotionEntity> promotionEntities = promotionService.getActivePromotions();
        List<Long> promotionsIds = new ArrayList<>();
        promotionEntities.forEach(promotionEntity -> promotionsIds.add(promotionEntity.getId()));
        List<CategoryEntity> categoryEntities = categoryService.findAllEntityByPromotionIn(promotionsIds);
        List<Long> categoriesIds = new ArrayList<>();
        categoryEntities.forEach(categoryEntity -> categoriesIds.add(categoryEntity.getId()));
        List<ProductDTO> productsList = productService.findAllByCategoryIn(categoriesIds);
        return applyProductsPromotions(categoryEntities, productsList);
    }

    private List<CategoryProductPromotionDTO> applyProductsPromotions(List<CategoryEntity> categoryEntities, List<ProductDTO> productsList) {
        return productsList.stream().map(productDTO -> {
            CategoryEntity localCategoryEntity = categoryEntities.stream().filter(categoryEntity -> categoryEntity.getId().equals(productDTO.getCategoryId())).findAny().get();
            return buildCategoryProductPromotionDTO(productDTO, localCategoryEntity);
        }).collect(Collectors.toList());
    }

    private CategoryProductPromotionDTO buildCategoryProductPromotionDTO(ProductDTO product, CategoryEntity category){
        return CategoryProductPromotionDTO.builder()
                .productName(product.getName())
                .originalPrice(product.getPrice())
                .promotionalPrice(product.getPrice().subtract(product.getPrice().multiply(category.getPromotion().getPercent().divide(BigDecimal.valueOf(100)))))
                .urlImage(product.getUrlImage())
                .percentOff(category.getPromotion().getPercent().toString().concat("%"))
                .build();
    }
}
