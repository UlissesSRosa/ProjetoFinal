package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.clients.UserClient;
import com.example.managementservice.domains.dtos.ShoppingCartDTO;
import com.example.managementservice.domains.dtos.UserDTO;
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
import org.apache.catalina.User;
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
        log.info("Registro de carrinho {}, updated carrinho {}", shoppingCartEntity.getRegister(), shoppingCartEntity.getUpdatedRegister());
        log.info("Vai busca o produto {}", shoppingCartRequestDTO.getProductId());
        ProductEntity product = productServiceImpl.findById(shoppingCartRequestDTO.getProductId());
        log.info("Vai busca o produto nome {} descrição {} preço {}", product.getName(), product.getDescription(), product.getPrice());
        log.info("Vai busca o user {}", shoppingCartRequestDTO.getUserId());
        UserEntity user = userClient.getUser(shoppingCartRequestDTO.getUserId());
        shoppingCartEntity.setUser(user);
        log.info("Vai busca o user name {}  cpf {}", user.getName(), user.getCpf());
        log.info("qnt produtos no carrinho {}", shoppingCartEntity.getProducts().size());
        shoppingCartEntity.setTotalAmount(product.getPrice());
        shoppingCartEntity.setStatus(shoppingCartRequestDTO.getStatus());
        log.info("Status {}", shoppingCartEntity.getStatus());
        log.info("atributos do carrinho {} {} {} {} {}", shoppingCartEntity.getProducts(), shoppingCartEntity.getStatus(), shoppingCartEntity.getTotalAmount(), shoppingCartEntity.getPayableAmount(), shoppingCartEntity.getUser());
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
