package com.example.managementservice.domains.resources;

import com.example.managementservice.domains.dtos.CategoryProductPromotionDTO;
import com.example.managementservice.domains.services.CategoryProductService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = "Promotional Products by Category")
@RequestMapping(value = "v1/promotional/products")
public class CategoryProductPromotionResource {

    private final CategoryProductService categoryProductService;

    @GetMapping
    public ResponseEntity<List<CategoryProductPromotionDTO>> getPromotionalProducts(){
        return ResponseEntity.ok(categoryProductService.getPromotionalProducts());
    }
}
