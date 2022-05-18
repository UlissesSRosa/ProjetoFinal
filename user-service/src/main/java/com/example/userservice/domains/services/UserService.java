package com.example.userservice.domains.services;

<<<<<<< HEAD
import com.example.userservice.domains.entities.UserEntity;
=======
import java.util.List;

>>>>>>> 76d06f804e8636a60fa7c1f8ecaa3b95479d2348
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.userservice.domains.dtos.requests.UserRequestDTO;
import com.example.userservice.domains.dtos.requests.UserUpdateDTO;
import com.example.userservice.domains.dtos.responses.UserDTO;

public interface UserService {
	
	public UserDTO findById(Long id);

    public UserEntity findByIdInternal(Long id);

    public Page<UserDTO> findAll(Pageable pageable);

    public void create(UserRequestDTO userRequestDTO);
    
    public void update(UserUpdateDTO userUpdateDTO);
    
    public void delete(Long id);
    
    public List<String> findAllEmails();
}
