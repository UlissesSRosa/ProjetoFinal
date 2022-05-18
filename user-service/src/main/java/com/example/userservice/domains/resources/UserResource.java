package com.example.userservice.domains.resources;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.domains.dtos.requests.UserRequestDTO;
import com.example.userservice.domains.dtos.responses.UserDTO;
import com.example.userservice.domains.services.UserService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(tags = "User")
@RequestMapping(value = "v1/users")
public class UserResource {

	private final UserService userService;

	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserDTO> findOne(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.findById(userId));
	}

	@GetMapping(value = "/all")
	public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(userService.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UserRequestDTO userRequestDTO) {
		userService.create(userRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/allEmails")
	public ResponseEntity<List<String>> getAllEmails(){
		return ResponseEntity.ok(userService.findAllEmails());
	}

}
