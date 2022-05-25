package com.example.orderservice.domains.clients;

import com.example.orderservice.domains.dtos.ShoppingCartDTO;
import com.example.orderservice.domains.entities.CategoryEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MANAGEMENT-SERVICE", url = "http://localhost:8082/")
public interface ManagementClient {

    @GetMapping(value = "v1/carts/{cartId}")
    ShoppingCartDTO getCart(@PathVariable Long cartId);

    @GetMapping(value = "v1/category/{categoryId}")
    CategoryEntity findCategory(@PathVariable Long categoryId);

    @DeleteMapping(value = "v1/carts/{cartId}")
    void deleteCart(@PathVariable Long cartId);


}
