package com.example.managementservice.domains.resources;


import com.example.managementservice.domains.entities.CategoryEntity;
import com.example.managementservice.domains.services.CategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Api(tags = "Management")
@RequestMapping(value = "v1/category")
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryEntity> getOneCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
}
