package com.example.managementservice.domains.repositories;

import com.example.managementservice.domains.entities.PromotionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRespository extends JpaRepository<PromotionEntity, Long> {

    Page<PromotionEntity> findByIsActiveTrue(Pageable pageable);

}
