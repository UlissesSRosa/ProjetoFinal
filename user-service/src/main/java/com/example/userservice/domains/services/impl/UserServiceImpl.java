package com.example.userservice.domains.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.userservice.domains.dtos.requests.UserRequestDTO;
import com.example.userservice.domains.dtos.requests.UserUpdateDTO;
import com.example.userservice.domains.dtos.responses.UserDTO;
import com.example.userservice.domains.entities.RoleEntity;
import com.example.userservice.domains.entities.UserEntity;
import com.example.userservice.domains.repositories.UserRepository;
import com.example.userservice.domains.services.RoleService;
import com.example.userservice.domains.services.UserService;
import com.example.userservice.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ObjectMapper objectMapper;
	private final RoleService roleService;

	/**
	 * Método que busca um usuário pela sua identificação.
	 * @param id userId
	 * @return UserDTO
	 */
	public UserDTO findById(Long id) {
		log.info("BUSCANDO O PRODUTO DE ID {} NA BASE DA DADOS", id);
		UserEntity userEntity = userRepository.findById(id).orElseThrow(NotFoundException::new);
		return buildUserDTO(userEntity);
	}

	public UserEntity findByIdInternal(Long id) {
		log.info("BUSCANDO O PRODUTO DE ID {} NA BASE DA DADOS", id);
		UserEntity userEntity = userRepository.findById(id).orElseThrow(NotFoundException::new);
		return userEntity;
	}

	public Page<UserDTO> findAll(Pageable pageable) {
		log.info("BUSCANDO TODOS OS PRODUTOS DA BASE DA DADOS");
		Page<UserEntity> userEntity = onValidateUserPage(pageable);
		return convertPageEntityToDTO(userEntity);
	}

	@Transactional
	public void create(UserRequestDTO userRequestDTO) {	
		log.info("CRIANDO Usuário {}", userRequestDTO.getName());
		UserEntity user = buildUserEntity(userRequestDTO);
		userRepository.save(user);
	}
	
	@Transactional
	public void update(UserUpdateDTO userUpdateDTO) {
		UserEntity currentUser = userRepository.findById(userUpdateDTO.getId()).orElseThrow(NotFoundException::new);
		UserEntity updatedUser = updateUser(userUpdateDTO, currentUser);
		userRepository.save(updatedUser);
 	}

	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public List<String> findAllEmails(){
		return userRepository.findAllEmails();
	}

	private UserEntity buildUserEntity(UserRequestDTO userRequestDTO) {
		return UserEntity.builder()
				.name(userRequestDTO.getName())
				.email(userRequestDTO.getEmail())
				.phone(userRequestDTO.getPhone())
				.cpf(userRequestDTO.getCpf())
				.password(userRequestDTO.getPassword()) // Verificar como criptografar essa senha
              	.role(objectMapper.convertValue(roleService.findById(userRequestDTO.getRoleID()), RoleEntity.class))
				.build();
	}
	
	private Page<UserEntity> onValidateUserPage(Pageable pageable) {
		Page<UserEntity> userEntity = userRepository.findAll(pageable);
		if (Objects.isNull(userEntity) && userEntity.isEmpty()) {
			log.info("NENHUM PRODUTO ENCONTRADO");
			throw new NotFoundException();
		}
		return userEntity;
	}
	
	private UserEntity updateUser(UserUpdateDTO userUpdateDTO, UserEntity currentUser) {
		currentUser.setUpdatedRegister(LocalDateTime.now());
		currentUser.setName(userUpdateDTO.getName());
		currentUser.setEmail(userUpdateDTO.getEmail());
		currentUser.setCpf(userUpdateDTO.getCpf());
		currentUser.setPassword(userUpdateDTO.getPassword());
		currentUser.setPhone(userUpdateDTO.getPhone());
		currentUser.setRole(objectMapper.convertValue(roleService.findById(userUpdateDTO.getRoleID()), RoleEntity.class));
		return currentUser;
	}
	
	private UserDTO buildUserDTO(UserEntity userEntity) {
		return UserDTO.builder()
				.id(userEntity.getId())
				.name(userEntity.getName())
				.email(userEntity.getEmail())
				.cpf(userEntity.getCpf())
				.phone(userEntity.getPhone())
				.roleID(userEntity.getRole().getId())
				.build();		
	}
	
	private Page<UserDTO> convertPageEntityToDTO(Page<UserEntity> userEntity) {
		return userEntity.map(new Function<UserEntity, UserDTO>() {
			@Override
			public UserDTO apply(UserEntity user) {
				UserDTO dto = buildUserDTO(user);
				return dto;
			}
		});
	}
}
