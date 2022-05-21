package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.dtos.ProductDTO;
import com.example.managementservice.domains.entities.ProductEntity;
import com.example.managementservice.domains.exceptions.NotFoundException;
import com.example.managementservice.domains.repositories.ProductRepository;
import com.example.managementservice.domains.services.CategoryService;
import com.example.managementservice.domains.services.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;
    private final CategoryService categoryService;

    public ProductEntity findById(Long id){
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public ProductDTO findByName(String name){
        ProductEntity productEntity = productRepository.findByNameLike(name);
        return objectMapper.convertValue(productEntity, ProductDTO.class);
    }

    public Page<ProductDTO> findAll(Pageable pageable){
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        return objectMapper.convertValue(productEntityPage.getContent(), new TypeReference<Page<ProductDTO>>() {
        });
    }

    public List<ProductDTO> findAllByCategoryIn(List<Long> categoryIds){
        List<ProductEntity> productEntities = productRepository.findProductEntitiesByCategoryIdIn(categoryIds);
        return productEntities.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void create(ProductDTO productDTO){
        productRepository.save(buildProductEntity(productDTO));
    }

    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id);
    }

    protected ProductEntity buildProductEntity(ProductDTO productDTO){
        return ProductEntity.builder()
                .categoryId(categoryService.findById(productDTO.getCategoryId()))
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .weigth(productDTO.getWeigth())
                .stock(productDTO.getStock())
                .urlImage(productDTO.getUrlImage())
                .build();
    }

    protected ProductDTO convertEntityToDTO(ProductEntity productEntity){
        return ProductDTO.builder()
                .description(productEntity.getDescription())
                .name(productEntity.getName())
                .stock(productEntity.getStock())
                .urlImage(productEntity.getUrlImage())
                .weigth(productEntity.getWeigth())
                .price(productEntity.getPrice())
                .categoryId(productEntity.getCategoryId().getId())
                .build();
    }
}

