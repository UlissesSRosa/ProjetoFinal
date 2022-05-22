package com.example.managementservice.domains.entities;


import com.example.managementservice.domains.dtos.UserDTO;
import com.example.managementservice.domains.enums.OrderStatus;
import com.example.managementservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "shoppingCarts", schema = "store")
public class ShoppingCartEntity extends BaseEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;


    @OneToMany(fetch = FetchType.EAGER)
    private List<ProductEntity> products;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = true)
    private BigDecimal payableAmount;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

}
