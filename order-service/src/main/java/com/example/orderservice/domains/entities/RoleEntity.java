package com.example.orderservice.domains.entities;

import com.example.orderservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
