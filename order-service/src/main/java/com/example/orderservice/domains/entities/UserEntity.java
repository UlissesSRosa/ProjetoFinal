package com.example.orderservice.domains.entities;

import com.example.orderservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "users", schema = "authentication", uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "email"})})
public class UserEntity extends BaseEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String password;

    private String phone;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AdressEntity> adress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private RoleEntity role;

}
