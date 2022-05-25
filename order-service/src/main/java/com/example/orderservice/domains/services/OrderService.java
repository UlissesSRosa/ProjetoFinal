package com.example.orderservice.domains.services;

import com.example.orderservice.domains.dtos.OrderDTO;

import java.util.List;

public interface OrderService {

    public List<OrderDTO> getAllByUser(Long userId);

    public OrderDTO create(Long cartId, Long userId);
}
