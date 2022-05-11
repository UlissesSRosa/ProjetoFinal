package com.example.userservice.domains.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.domains.dtos.responses.RoleDTO;
import com.example.userservice.domains.services.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Api(tags = "Role")
@RequestMapping(value = "v1/permissions")
public class RoleResource {

	private final RoleService roleServiceService;

	@GetMapping(value = "/{permissionId}")
	public ResponseEntity<RoleDTO> findOne(@PathVariable Long permissionId) {
		return ResponseEntity.ok(roleServiceService.findById(permissionId));
	}
	
	@GetMapping(value = "/all")
	@ApiOperation(value = "Find all Roles")
	public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(roleServiceService.findAll(pageable));
	}
	
	@PostMapping
    public ResponseEntity<Void> create(@RequestBody RoleDTO permissionDTO){
		roleServiceService.create(permissionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
