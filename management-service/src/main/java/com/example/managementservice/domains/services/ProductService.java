package com.example.managementservice.domains.services;

import com.example.managementservice.domains.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    public ProductDTO findByName(String name);

    public Page<ProductDTO> findAll(Pageable pageable);

    public void create(ProductDTO productDTO);
}
