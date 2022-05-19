package com.example.managementservice.domains.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.managementservice.domains.dtos.ProductDTO;
import com.example.managementservice.domains.services.ProductService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(tags = "User")
@RequestMapping(value = "v1/products")
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductDTO> findAny(@RequestParam String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
