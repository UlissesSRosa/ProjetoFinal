package com.example.userservice.domains.dtos.responses;

import java.util.Objects;

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
public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private Long roleID;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO that = (UserDTO) o;
        return Objects.equals(id, that.id);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

 
