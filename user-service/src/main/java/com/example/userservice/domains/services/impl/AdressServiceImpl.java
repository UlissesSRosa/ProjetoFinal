package com.example.userservice.domains.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.userservice.domains.dtos.responses.AdressDTO;
import com.example.userservice.domains.dtos.responses.UserDTO;
import com.example.userservice.domains.entities.AdressEntity;
import com.example.userservice.domains.entities.UserEntity;
import com.example.userservice.domains.repositories.AdressRepository;
import com.example.userservice.domains.services.AdressService;
import com.example.userservice.domains.services.UserService;
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
	
//	protected List<AdressDTO> buildDTOAdress(List<AdressEntity> adressEntity){
////		return adressEntity.stream().map(adress -> {
////		    AdressDTO adressDto = new AdressDTO();
////		    adressDto.setZipCode(adress.getZipCode());
////		    adressDto.setStreet(adress.getStreet());
////		    adressDto.setNumber(adress.getNumber());
////		    adressDto.setAdressComplement(adress.getAdressComplement());
////		    adressDto.setDistrict(adress.getDistrict());
////		    adressDto.setCity(adress.getCity());
////		    adressDto.setState(adress.getState());
////		    adressDto.setCountry(adress.getCountry());
////		    adressDto.setCurrentAdress(adress.getCurrentAdress());
////		    adressDto.ser
////		    return adressDto;
////		}).collect(Collectors.toList());
//	}
	
}
