package com.example.userservice.domains.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.domains.entities.AdressEntity;

public interface AdressRepository extends JpaRepository<AdressEntity, Long> {
	
	List<AdressEntity> findAllByUserId(Long userId);
	
}
