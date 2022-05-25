package com.example.userservice.domains.services;

import com.example.userservice.domains.dtos.requests.UserRequestDTO;
import com.example.userservice.domains.dtos.requests.UserUpdateDTO;
import com.example.userservice.domains.dtos.responses.UserDTO;
import com.example.userservice.domains.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
	
	public UserDTO findById(Long id);

	public UserEntity findEntityById(Long id);

    public Page<UserDTO> findAll(Pageable pageable);

    public void create(UserRequestDTO userRequestDTO);
    
    public void update(UserUpdateDTO userUpdateDTO);
    
    public void delete(Long id);
    
    public List<String> findAllEmails();
}
