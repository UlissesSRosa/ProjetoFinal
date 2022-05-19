package com.example.managementservice.domains.services;

import com.example.managementservice.domains.dtos.requests.PromotionRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {

    public void create(PromotionRequestDTO promotionRequestDTO);

    public void delete(Long id);

    public Page<PromotionRequestDTO> getAll(Pageable pageable);
}
