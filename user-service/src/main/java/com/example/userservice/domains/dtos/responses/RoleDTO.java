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
public class RoleDTO {
	
	private Long id;
	private String roleName;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO that = (RoleDTO) o;
        return Objects.equals(id, that.id);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
}
