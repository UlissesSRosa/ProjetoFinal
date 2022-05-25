package com.example.orderservice.domains.services;

import com.example.orderservice.domains.dtos.ProductDTO;
import com.example.orderservice.domains.entities.ProductEntity;

public interface ProductService {

    public ProductEntity buildProductEntity(ProductDTO productDTO);

    public ProductDTO convertProductEntityToDTO(ProductEntity productEntity);
}
