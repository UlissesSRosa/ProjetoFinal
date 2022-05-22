package com.example.managementservice.domains.services;

import com.example.managementservice.domains.dtos.ShoppingCartDTO;
import com.example.managementservice.domains.dtos.requests.ShoppingCartRequestDTO;

public interface ShoppingCartService {

    public ShoppingCartDTO findById(Long id);

    public void create(ShoppingCartRequestDTO shoppingCartRequestDTO);

    public ShoppingCartDTO addProduct(Long cartId, Long productId);

    public ShoppingCartDTO removeProduct(Long cartId, Long productId);

    public void delete(Long cartId);
}
