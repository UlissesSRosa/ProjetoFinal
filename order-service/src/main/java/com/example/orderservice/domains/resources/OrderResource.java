package com.example.orderservice.domains.resources;

import com.example.orderservice.domains.dtos.OrderDTO;
import com.example.orderservice.domains.services.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = "Order")
@RequestMapping(value = "v1/order")
public class OrderResource {

    private final OrderService orderService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<OrderDTO>> getAllByUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getAllByUser(userId));
    }

    @PostMapping(value = "/{cartId}/{userId}")
    public ResponseEntity<Void> getAllByUser(@PathVariable Long cartId, @PathVariable Long userId){
        orderService.create(cartId, userId);
        return ResponseEntity.noContent().build();
    }
}
