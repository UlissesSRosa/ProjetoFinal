package com.example.managementservice.domains.services.impl;

import com.example.managementservice.domains.dtos.requests.PromotionRequestDTO;
import com.example.managementservice.domains.entities.PromotionEntity;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRespository promotionRespository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void create(PromotionRequestDTO promotionRequestDTO) {
        promotionRespository.save(objectMapper.convertValue(promotionRequestDTO, PromotionEntity.class));
    }

    @Transactional
    public void delete(Long id) {
        promotionRespository.deleteById(id);
    }

    public Page<PromotionRequestDTO> getAll(Pageable pageable) {
        Page<PromotionEntity> promotionEntitiesPage = promotionRespository.findByIsActiveTrue(pageable);
        return objectMapper.convertValue(promotionEntitiesPage.getContent(), new TypeReference<Page<PromotionRequestDTO>>() {
        });
    }
}
