package com.example.managementservice.domains.services;

import com.example.managementservice.domains.dtos.requests.PromotionRequestDTO;
import com.example.managementservice.domains.entities.PromotionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromotionService {

    public PromotionEntity findById(Long id);

    public Page<PromotionRequestDTO> getAll(Pageable pageable);

    public List<PromotionEntity> getActivePromotions();

    public void create(PromotionRequestDTO promotionRequestDTO);

    public void delete(Long id);

}
