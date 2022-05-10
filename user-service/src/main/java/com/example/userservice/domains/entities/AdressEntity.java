package com.example.userservice.domains.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.userservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "adress", schema = "authentication")
//@Table(name = "adress", schema = "store", uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "email"})})
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
