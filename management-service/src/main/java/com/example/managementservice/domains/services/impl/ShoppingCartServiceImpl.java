package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.clients.UserClient;
import com.example.managementservice.domains.dtos.ProductDTO;
import com.example.managementservice.domains.dtos.ShoppingCartDTO;
import com.example.managementservice.domains.dtos.UserDTO;
import com.example.managementservice.domains.dtos.requests.ShoppingCartRequestDTO;
import com.example.managementservice.domains.entities.CategoryEntity;
import com.example.managementservice.domains.entities.ProductEntity;
import com.example.managementservice.domains.entities.ShoppingCartEntity;
import com.example.managementservice.domains.entities.UserEntity;
import com.example.managementservice.domains.enums.OrderStatus;
import com.example.managementservice.domains.exceptions.NotFoundException;
import com.example.managementservice.domains.repositories.ShoppingCartRespository;
import com.example.managementservice.domains.services.CategoryService;
import com.example.managementservice.domains.services.ShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final ShoppingCartRespository shoppingCartRespository;
    private final ObjectMapper objectMapper;
    private final ProductServiceImpl productServiceImpl;
    private final CategoryService categoryService;
    private final UserClient userClient;


    public ShoppingCartDTO findById(Long id) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRespository.findById(id).orElseThrow(NotFoundException::new);
        return objectMapper.convertValue(shoppingCartEntity, ShoppingCartDTO.class);
    }

    public void create(ShoppingCartRequestDTO shoppingCartRequestDTO) {
        ProductEntity product = productServiceImpl.findById(shoppingCartRequestDTO.getProductId());
        ShoppingCartEntity shoppingCartEntity = buildNewCart(product);
        UserEntity user = userClient.getUser(shoppingCartRequestDTO.getUserId());
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(product);
        shoppingCartEntity.setProducts(productEntities);
        shoppingCartEntity.setUser(user);
        shoppingCartRespository.save(shoppingCartEntity);
    }


    @Transactional
    public ShoppingCartDTO addProduct(Long cartId, Long productId){
        ShoppingCartEntity cart = shoppingCartRespository.findById(cartId).orElseThrow(NotFoundException::new);
        ProductEntity product = productServiceImpl.findById(productId);
        CategoryEntity categoryEntity = categoryService.findById(product.getCategoryId().getId());
        product.getPrice().subtract(product.getPrice().multiply(categoryEntity.getPromotion().getPercent().divide(BigDecimal.valueOf(100))));
        cart.setPayableAmount(cart.getPayableAmount().add(product.getPrice()));
        cart.setTotalAmount(cart.getTotalAmount().add(product.getPrice()));
        cart.getProducts().add(product);
        ShoppingCartEntity newCart = shoppingCartRespository.save(cart);
        return convertEntityToDTO(newCart);

    }

    @Transactional
    public ShoppingCartDTO removeProduct(Long cartId, Long productId){
        ShoppingCartEntity cart = shoppingCartRespository.findById(cartId).orElseThrow(NotFoundException::new);
        ProductEntity product = productServiceImpl.findById(productId);
        CategoryEntity categoryEntity = categoryService.findById(product.getCategoryId().getId());
        product.getPrice().subtract(product.getPrice().multiply(categoryEntity.getPromotion().getPercent().divide(BigDecimal.valueOf(100))));
        cart.setTotalAmount(cart.getTotalAmount().subtract(product.getPrice()));
        cart.getProducts().remove(product);
        ShoppingCartEntity newCart = shoppingCartRespository.save(cart);
        return convertEntityToDTO(newCart);
    }

    @Transactional
    public void delete(Long cartId){
        shoppingCartRespository.deleteById(cartId);
    }

    private ShoppingCartEntity buildNewCart(ProductEntity productEntity){
        return ShoppingCartEntity.builder()
                .status(OrderStatus.OPEN)
                .payableAmount(productEntity.getPrice())
                .totalAmount(productEntity.getPrice())
                .build();
    }

    private ShoppingCartDTO convertEntityToDTO(ShoppingCartEntity shoppingCartEntity){
        return ShoppingCartDTO.builder()
                .cartId(shoppingCartEntity.getId())
                .payableAmount(shoppingCartEntity.getPayableAmount())
                .totalAmount(shoppingCartEntity.getTotalAmount())
                .products(shoppingCartEntity.getProducts().stream().map(productServiceImpl::convertEntityToDTO).collect(Collectors.toList()))
                .status(shoppingCartEntity.getStatus())
                .userId(shoppingCartEntity.getUser().getId())
                .build();
    }
}
