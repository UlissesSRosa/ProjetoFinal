package com.example.managementservice.domains.resources;

import com.example.managementservice.domains.dtos.ShoppingCartDTO;
import com.example.managementservice.domains.dtos.requests.ShoppingCartRequestDTO;
import com.example.managementservice.domains.services.ShoppingCartService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Management")
@RequestMapping(value = "v1/carts")
public class ShoppingCartResource {

    private final ShoppingCartService shoppingCartService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ShoppingCartDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ShoppingCartRequestDTO shoppingCartRequestDTO){
        shoppingCartService.create(shoppingCartRequestDTO);
        return ResponseEntity.noContent().build();
    }

}
