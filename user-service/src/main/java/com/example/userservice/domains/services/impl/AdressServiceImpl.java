package com.example.userservice.domains.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ctc.wstx.shaded.msv_core.grammar.ConcurExp;
import com.example.userservice.domains.dtos.responses.AdressDTO;
import com.example.userservice.domains.dtos.responses.UserDTO;
import com.example.userservice.domains.entities.AdressEntity;
import com.example.userservice.domains.entities.UserEntity;
import com.example.userservice.domains.repositories.AdressRepository;
import com.example.userservice.domains.services.AdressService;
import com.example.userservice.domains.services.UserService;
import com.example.userservice.exceptions.NotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdressServiceImpl implements AdressService {

	private final ObjectMapper objectMapper;
	private final AdressRepository adressRepository;
	private final UserService userService;

	/**
	 * Método de busca de Usuário pela sua identificação.
	 * 
	 * @param userId
	 * @return
	 */
	public List<AdressDTO> findAllByUserId(Long userId) {
		log.info("BUSCANDO O ENDEREÇO DO USUARIO COM O ID {} NA BASE DA DADOS", userId);
		List<AdressDTO> adressDTOList = objectMapper.convertValue(adressRepository.findAllByUserId(userId), new TypeReference<List<AdressDTO>>() {});
		return adressDTOList;
	}
	
	public void create(AdressDTO adressDTO) {
		log.info("CRIANDO UM ENDEREÇO DO USUARIO COM O ID {} NA BASE DA DADOS", adressDTO.getUserID());
		AdressEntity adress = objectMapper.convertValue(adressDTO, AdressEntity.class);
		UserDTO userDTO = userService.findById(adressDTO.getUserID());
		adress.setUser(objectMapper.convertValue(userDTO, UserEntity.class)); 
		adressRepository.save(adress);
	}
	
	@Transactional
	public void update(AdressDTO adressDTO) {
		log.info("ATUALIZANDO O ENDEREÇO DO USUARIO COM O ID {} NA BASE DA DADOS", adressDTO.getUserID());
		AdressEntity currentAdress = adressRepository.findById(adressDTO.getId()).orElseThrow(NotFoundException::new);
		AdressEntity updatedAdress = updateAdress(adressDTO, currentAdress);
		adressRepository.save(updatedAdress);
	}
	
	@Transactional
	public void delete(Long id) {
		adressRepository.deleteById(id);
	}

	private AdressEntity updateAdress(AdressDTO adressDTO, AdressEntity currentAdress) {
		currentAdress.setUpdatedRegister(LocalDateTime.now());
		currentAdress.setZipCode(adressDTO.getZipCode());
		currentAdress.setStreet(adressDTO.getStreet());
		currentAdress.setNumber(adressDTO.getNumber());
		currentAdress.setAdressComplement(adressDTO.getAdressComplement());
		currentAdress.setDistrict(adressDTO.getDistrict());
		currentAdress.setCity(adressDTO.getCity());
		currentAdress.setState(adressDTO.getState());
		currentAdress.setCountry(adressDTO.getCountry());
		currentAdress.setCurrentAdress(adressDTO.getCurrentAdress());
		return currentAdress;
	}
	
}
