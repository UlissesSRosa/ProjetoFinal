package com.example.userservice.domains.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UserRequestDTO {
	
	private String name;
	private String email;
	private String cpf;	
	private String password;
	private String phone;
	private Long roleID;
	
}

 
