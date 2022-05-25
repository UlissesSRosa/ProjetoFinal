package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.dtos.requests.PromotionRequestDTO;
import com.example.managementservice.domains.entities.PromotionEntity;
import com.example.managementservice.domains.exceptions.NotFoundException;
import com.example.managementservice.domains.repositories.PromotionRespository;
import com.example.managementservice.domains.services.PromotionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRespository promotionRespository;
    private final ObjectMapper objectMapper;

    public PromotionEntity findById(Long id){
        return promotionRespository.findById(id).orElseThrow(NotFoundException::new);
    }
    public Page<PromotionRequestDTO> getAll(Pageable pageable) {
        Page<PromotionEntity> promotionEntitiesPage = promotionRespository.findByIsActiveTrue(pageable);
        return promotionEntitiesPage.map(this::buildDTO);
    }

    public List<PromotionEntity> getActivePromotions(){
        return promotionRespository.findAllByIsActiveTrue();
    }

    @Transactional
    public void create(PromotionRequestDTO promotionRequestDTO) {
        promotionRespository.save(buildEntity(promotionRequestDTO));
    }

    @Transactional
    public void delete(Long id) {
        promotionRespository.deleteById(id);
    }


    public PromotionRequestDTO buildDTO(PromotionEntity promotion) {
        return PromotionRequestDTO.builder()
                .name(promotion.getName())
                .isActive(promotion.getIsActive())
                .percent(promotion.getPercent())
                .build();
    }

    public PromotionEntity buildEntity(PromotionRequestDTO promotion) {
        return PromotionEntity.builder()
                .name(promotion.getName())
                .isActive(promotion.getIsActive())
                .percent(promotion.getPercent())
                .build();
    }


}
