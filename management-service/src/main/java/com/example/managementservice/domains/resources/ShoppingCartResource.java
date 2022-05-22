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

    @PatchMapping(value = "/add")
    public ResponseEntity<ShoppingCartDTO> addProduct(@RequestParam Long cartId ,@RequestParam Long productId){
        return ResponseEntity.ok(shoppingCartService.addProduct(cartId, productId));
    }

    @PatchMapping(value = "/remove")
    public ResponseEntity<ShoppingCartDTO> removeProduct(@RequestParam Long cartId ,@RequestParam Long productId){
        return ResponseEntity.ok(shoppingCartService.removeProduct(cartId, productId));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        shoppingCartService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
