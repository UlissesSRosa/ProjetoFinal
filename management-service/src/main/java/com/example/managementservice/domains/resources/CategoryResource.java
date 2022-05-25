package com.example.managementservice.domains.resources;


import com.example.managementservice.domains.dtos.CategoryDTO;
import com.example.managementservice.domains.dtos.requests.CategoryRequestDTO;
import com.example.managementservice.domains.entities.CategoryEntity;
import com.example.managementservice.domains.services.CategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Category")
@RequestMapping(value = "v1/category")
public class CategoryResource {

    private final CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryEntity> getOneCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Page<CategoryDTO>> getOneCategory(Pageable pageable){
        return ResponseEntity.ok(categoryService.findAllPageable(pageable));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryRequestDTO categoryRequestDTO){
        return ResponseEntity.ok(categoryService.create(categoryRequestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
