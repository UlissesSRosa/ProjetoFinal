package com.example.managementservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteEndpoint {
	
	@GetMapping(value = "/teste")
	public String testar(){
		return "Teste Ok";
	}

}
