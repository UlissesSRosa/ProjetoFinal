package com.example.managementservice.domains.resources;

import com.example.managementservice.domains.dtos.requests.PromotionRequestDTO;
import com.example.managementservice.domains.services.PromotionService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "Promotion")
@RequestMapping(value = "v1/promotions")
public class PromotionResource {

    private final PromotionService promotionService;

    @GetMapping
    public ResponseEntity<Page<PromotionRequestDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(promotionService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Void> create(PromotionRequestDTO promotionRequestDTO){
        promotionService.create(promotionRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id){
        promotionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
