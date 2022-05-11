package com.example.userservice.domains.entities;

import java.io.Serializable;

import javax.persistence.Entity;
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
@Table(name = "roles", schema = "authentication")
public class RoleEntity extends BaseEntityModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String roleName;

}
