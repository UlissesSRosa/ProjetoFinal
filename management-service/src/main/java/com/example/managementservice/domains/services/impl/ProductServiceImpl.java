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

    @Transactional
    public void create(ProductDTO productDTO){
        ProductEntity product = objectMapper.convertValue(productDTO, ProductEntity.class);
        productRepository.save(product);
    }

    protected ProductEntity convertDTOtoEntity(ProductDTO productDTO){
        return ProductEntity.builder()
                .categoryId(categoryService.findById(productDTO.getCategoryId()))
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .weigth(productDTO.getWeigth())
                .stock(productDTO.getStock())
                .url_image(productDTO.getUrl_image())
                .build();
    }
}

