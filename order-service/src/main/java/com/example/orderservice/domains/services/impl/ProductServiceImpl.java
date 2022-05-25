package com.example.orderservice.domains.services.impl;

import com.example.orderservice.domains.clients.ManagementClient;
import com.example.orderservice.domains.dtos.ProductDTO;
import com.example.orderservice.domains.entities.ProductEntity;
import com.example.orderservice.domains.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ManagementClient managementClient;

    public ProductEntity buildProductEntity(ProductDTO productDTO){
        return ProductEntity.builder()
                .categoryId(managementClient.findCategory(productDTO.getCategoryId()))
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .weigth(productDTO.getWeigth())
                .stock(productDTO.getStock())
                .urlImage(productDTO.getUrlImage())
                .build();
    }

    public ProductDTO convertProductEntityToDTO(ProductEntity productEntity){
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
