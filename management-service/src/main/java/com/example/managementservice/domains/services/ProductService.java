package com.example.managementservice.domains.services;

import com.example.managementservice.domains.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public ProductDTO findByName(String name);

    public Page<ProductDTO> findAll(Pageable pageable);

    public List<ProductDTO> findAllByCategoryIn(List<Long> categoryIds);

    public void create(ProductDTO productDTO);

    public void delete(Long id);
}
