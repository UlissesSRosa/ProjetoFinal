package com.example.userservice.domains.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.domains.dtos.responses.AdressDTO;
import com.example.userservice.domains.services.AdressService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(tags = "Adress")
@RequestMapping(value = "v1/adress")
public class AdressResource {

	private final AdressService adressService;

	@GetMapping(value = "/{userId}")
	public ResponseEntity<List<AdressDTO>> findAllByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(adressService.findAllByUserId(userId));
	}
	
	@PostMapping
    public ResponseEntity<Void> create(@RequestBody AdressDTO adressDTO){
		adressService.create(adressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
