package com.example.userservice.domains.services.impl;

import java.util.Objects;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.userservice.domains.dtos.responses.RoleDTO;
import com.example.userservice.domains.entities.RoleEntity;
import com.example.userservice.domains.repositories.RoleRepository;
import com.example.userservice.domains.services.RoleService;
import com.example.userservice.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoleImpl implements RoleService {

	private final ObjectMapper objectMapper;
	private final RoleRepository roleRepository;

	/**
	 * Método de busca de a permissão pelo seu Id.
	 * 
	 * @param id
	 * @return
	 */
	public RoleDTO findById(Long id) {
		log.info("BUSCANDO A PERMISSAO DE ID {} NA BASE DA DADOS", id);
		RoleEntity permissionEntity = roleRepository.findById(id).orElseThrow(NotFoundException::new);
		RoleDTO permissionDTO = objectMapper.convertValue(permissionEntity, RoleDTO.class);
		return permissionDTO;
	}
	
	/**
	 * Método de busca de todas as permissões.
	 * 
	 * @param permissionDTO
	 * @return
	 */
	public Page<RoleDTO> findAll(Pageable pageable) {
		log.info("BUSCANDO TODOS AS PERMISSOES DA BASE DA DADOS");
		Page<RoleEntity> roleEntity = roleRepository.findAll(pageable);
		if(Objects.isNull(roleEntity) && roleEntity.isEmpty()) {
			log.info("NENHUMA PERMISSAO ENCONTRADO");
            throw new NotFoundException();
		}
		Page<RoleDTO> permissionDTOPage = buildPageDTO(roleEntity);
		return permissionDTOPage;
	}
	
	 /**
     * Método de criação de novas permissões.
     * @param permissionDTO
     */
    @Transactional
    public void create(RoleDTO roleDTO){
        log.info("CRIANDO A PERMISSAO {}", roleDTO.getRoleName());
        roleRepository.save(objectMapper.convertValue(roleDTO, RoleEntity.class));
    }
	
	private Page<RoleDTO> buildPageDTO(Page<RoleEntity> roleEntity) {
		return roleEntity.map(new Function<RoleEntity, RoleDTO>(){
			@Override
			public RoleDTO apply(RoleEntity roleEntity) {
				RoleDTO dto = new RoleDTO();
				dto.setId(roleEntity.getId());
				dto.setRoleName(roleEntity.getRoleName());
				return dto;
			}
		});
	}

}
