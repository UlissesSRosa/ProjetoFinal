package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.dtos.ProductDTO;
import com.example.managementservice.domains.entities.ProductEntity;
import com.example.managementservice.domains.exceptions.NotFoundException;
import com.example.managementservice.domains.repositories.ProductRepository;
import com.example.managementservice.domains.services.CategoryService;
import com.example.managementservice.domains.services.ProductService;
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

    /**
     * Método responsável por buscar produto por Id.
     * @param id
     * @return
     */
    public ProductEntity findById(Long id){
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * Método responsável por buscar produtos com nome similar ao informado.
     * @param name
     * @return
     */
    public List<ProductDTO> findByName(String name){
        List<ProductEntity> productEntities = productRepository.findAllByNameContains(name);
        return productEntities.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    /**
     * Método responsável por realiar busca paginada de todos os produtos.
     * @param pageable
     * @return
     */
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        return productEntityPage.map(this::convertEntityToDTO);
    }

    /**
     * Método responsável por buscar produtos pela categoria.
     * @param categoryIds
     * @return
     */
    public List<ProductDTO> findAllByCategoryIn(List<Long> categoryIds){
        List<ProductEntity> productEntities = productRepository.findProductEntitiesByCategoryIdIn(categoryIds);
        return productEntities.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    /**
     * Método responsável por criar um produto.
     * @param productDTO
     */
    @Transactional
    public void create(ProductDTO productDTO){
        productRepository.save(buildProductEntity(productDTO));
    }

    /**
     * Método responsável por deletar um produto a partir do Id.
     * @param id
     */
    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id);
    }

    /**
     * Método de construção de entidade com abse em DTO.
     * @param productDTO
     * @return
     */
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

    /**
     * Método de conversão para retorno de DTO.
     * @param productEntity
     * @return
     */
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

