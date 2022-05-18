package com.example.userservice.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.userservice.domains.dtos.requests.UserRequestDTO;
import com.example.userservice.domains.dtos.responses.UserDTO;

public interface UserService {
	
	public UserDTO findById(Long id);

    public Page<UserDTO> findAll(Pageable pageable);

    public void create(UserRequestDTO userRequestDTO);
    
//    public void update(UserDTO userDTO);
    
    public void delete(Long id);
    
    public List<String> findAllEmails();
}
