package com.example.userservice.domains.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.userservice.domains.dtos.responses.RoleDTO;

public interface RoleService {
	
	public RoleDTO findById(Long id);

    public Page<RoleDTO> findAll(Pageable pageable);
    
    public void create(RoleDTO roleDTO);
}
