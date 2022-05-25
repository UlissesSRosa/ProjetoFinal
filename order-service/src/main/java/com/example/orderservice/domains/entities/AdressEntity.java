package com.example.orderservice.domains.entities;

import com.example.orderservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "adress", schema = "authentication")
public class AdressEntity extends BaseEntityModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String zipCode;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private Long number;
	
	@Column(nullable = false)
	private String adressComplement;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String currentAdress;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

}
