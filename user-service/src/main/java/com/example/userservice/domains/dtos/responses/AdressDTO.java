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
public class AdressDTO {
	
	private Long id;
	private String zipCode;
	private String street;
	private Long number;
	private String adressComplement;
	private String district;
	private String city;
	private String state;	
	private String country;
	private String currentAdress;
	private Long userID;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdressDTO that = (AdressDTO) o;
        return Objects.equals(id, that.id);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(id);
    }
	
}
