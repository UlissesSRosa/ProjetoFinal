package com.example.userservice.domains.services;

import java.util.List;

import com.example.userservice.domains.dtos.responses.AdressDTO;

public interface AdressService {

	public List<AdressDTO> findAllByUserId(Long userId);
	
	public void create (AdressDTO adressDTO);
	
	public void update (AdressDTO adressDTO);
	
	public void delete(Long id);
}
