package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.dtos.CategoryDTO;
import com.example.managementservice.domains.dtos.requests.CategoryRequestDTO;
import com.example.managementservice.domains.entities.CategoryEntity;
import com.example.managementservice.domains.exceptions.NotFoundException;
import com.example.managementservice.domains.repositories.CategoryRepository;
import com.example.managementservice.domains.services.CategoryService;
import com.example.managementservice.domains.services.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final PromotionService promotionService;

    public CategoryEntity findById(Long id){
        return categoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<CategoryDTO> findAllPageable(Pageable pageable){
        Page<CategoryEntity> entityPage = categoryRepository.findAll(pageable);
        return buildPageDTO(entityPage);
    }

    public List<CategoryEntity> findAllEntityByPromotionIn(List<Long> promotionIds){
        return categoryRepository.findCategoryEntitiesByPromotionIdIn(promotionIds);
    }

    @Transactional
    public CategoryDTO create(CategoryRequestDTO categoryRequestDTO){
        CategoryEntity category = buildEntity(categoryRequestDTO);
        return buildDTO(categoryRepository.save(category));
    }

    @Transactional
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    protected CategoryEntity buildEntity(CategoryRequestDTO categoryRequestDTO){
        return CategoryEntity.builder()
                .name(categoryRequestDTO.getName())
                .promotion(promotionService.findById(categoryRequestDTO.getPromotionId()))
                .build();
    }

    protected CategoryDTO buildDTO(CategoryEntity category){
        return CategoryDTO.builder()
                .promotionId(category.getPromotion().getId())
                .name(category.getName())
                .id(category.getId())
                .build();
    }

    private Page<CategoryDTO> buildPageDTO(Page<CategoryEntity> categoryEntity) {
        return categoryEntity.map(new Function<CategoryEntity, CategoryDTO>(){
            @Override
            public CategoryDTO apply(CategoryEntity categoryEntity) {
                CategoryDTO dto = new CategoryDTO();
                dto.setId(categoryEntity.getId());
                dto.setName(categoryEntity.getName());
                dto.setPromotionId(categoryEntity.getPromotion().getId());
                return dto;
            }
        });
    }
}
