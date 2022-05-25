package com.example.orderservice.domains.services.impl;

import com.example.orderservice.domains.clients.ManagementClient;
import com.example.orderservice.domains.clients.UserClient;
import com.example.orderservice.domains.dtos.OrderDTO;
import com.example.orderservice.domains.dtos.ProductDTO;
import com.example.orderservice.domains.dtos.ShoppingCartDTO;
import com.example.orderservice.domains.entities.OrderEntity;
import com.example.orderservice.domains.entities.ProductEntity;
import com.example.orderservice.domains.entities.UserEntity;
import com.example.orderservice.domains.repositories.OrderRepository;
import com.example.orderservice.domains.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ManagementClient managementClient;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final UserClient userClient;

    public List<OrderDTO> getAllByUser(Long userId) {
        List<OrderEntity> entitiesList = orderRepository.getOrderEntitiesByUserId(userId);
        return entitiesList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO create(Long cartId, Long userId) {
        ShoppingCartDTO shoppingCartDTO = managementClient.getCart(cartId);
        UserEntity userEntity = userClient.getUser(userId);
        OrderEntity order = buildEntity(shoppingCartDTO, userEntity);
        OrderEntity newOrder = orderRepository.save(order);
        if (Objects.nonNull(newOrder.getId())) {
            managementClient.deleteCart(cartId);
        }
        return convertToDTO(newOrder);
    }

    private OrderEntity buildEntity(ShoppingCartDTO shoppingCartDTO, UserEntity user){
        return OrderEntity.builder()
                .productsList(shoppingCartDTO.getProducts().stream().map(this::buildProductEntity).collect(Collectors.toList()))
                .value(shoppingCartDTO.getPayableAmount())
                .user(user)
                .buyingDate(LocalDateTime.now())
                .build();
    }

    private OrderDTO convertToDTO(OrderEntity orderEntity){
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .productsList(orderEntity.getProductsList().stream().map(this::convertProductEntityToDTO).collect(Collectors.toList()))
                .userId(orderEntity.getUser().getId())
                .value(orderEntity.getValue())
                .buyingDate(orderEntity.getBuyingDate())
                .build();
    }

    protected ProductEntity buildProductEntity(ProductDTO productDTO){
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

    protected ProductDTO convertProductEntityToDTO(ProductEntity productEntity){
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
