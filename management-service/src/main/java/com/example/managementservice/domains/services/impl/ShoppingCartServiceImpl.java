package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.clients.UserClient;
import com.example.managementservice.domains.dtos.ShoppingCartDTO;
import com.example.managementservice.domains.dtos.requests.ShoppingCartRequestDTO;
import com.example.managementservice.domains.entities.ProductEntity;
import com.example.managementservice.domains.entities.ShoppingCartEntity;
import com.example.managementservice.domains.entities.UserEntity;
import com.example.managementservice.domains.exceptions.NotFoundException;
import com.example.managementservice.domains.repositories.ShoppingCartRespository;
import com.example.managementservice.domains.services.ShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final ShoppingCartRespository shoppingCartRespository;
    private final ObjectMapper objectMapper;
    private final ProductServiceImpl productServiceImpl;
    private final UserClient userClient;


    public ShoppingCartDTO findById(Long id) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRespository.findById(id).orElseThrow(NotFoundException::new);
        return objectMapper.convertValue(shoppingCartEntity, ShoppingCartDTO.class);
    }

    public void create(ShoppingCartRequestDTO shoppingCartRequestDTO) {
        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity();
        log.info("Vai busca o produto {}", shoppingCartRequestDTO.getProductId());
        ProductEntity product = productServiceImpl.findById(shoppingCartRequestDTO.getProductId());
        log.info("Vai busca o user {}", shoppingCartRequestDTO.getUserId());
        shoppingCartEntity.setUser(objectMapper.convertValue(userClient.getUser(shoppingCartRequestDTO.getUserId()), UserEntity.class));
        log.info("Nome {}", shoppingCartEntity.getUser().getName());
        shoppingCartEntity.getProducts().add(product);
        shoppingCartEntity.setTotalAmount(product.getPrice());
        shoppingCartEntity.setStatus(shoppingCartRequestDTO.getStatus());
        shoppingCartRespository.save(shoppingCartEntity);
    }


    @Transactional
    public ShoppingCartDTO addProduct(Long cartId, Long productId){
        ShoppingCartEntity cart = shoppingCartRespository.findById(cartId).orElseThrow(NotFoundException::new);
        ProductEntity product = productServiceImpl.findById(productId);
        cart.setTotalAmount(cart.getTotalAmount() + product.getPrice());
        cart.getProducts().add(product);
        return objectMapper.convertValue(shoppingCartRespository.save(cart), ShoppingCartDTO.class);
    }

    @Transactional
    public ShoppingCartDTO removeProduct(Long cartId, Long productId){
        ShoppingCartEntity cart = shoppingCartRespository.findById(cartId).orElseThrow(NotFoundException::new);
        ProductEntity product = productServiceImpl.findById(productId);
        cart.setTotalAmount(cart.getTotalAmount() - product.getPrice());
        cart.getProducts().remove(product);
        return objectMapper.convertValue(shoppingCartRespository.save(cart), ShoppingCartDTO.class);
    }

    @Transactional
    public void delete(Long cartId){
        shoppingCartRespository.deleteById(cartId);
    }

//    private ShoppingCartEntity buildNewCart(ShoppingCartRequestDTO shoppingCartRequestDTO){
//        return ShoppingCartEntity.builder()
//                .status(shoppingCartRequestDTO.getStatus())
//    }
}
