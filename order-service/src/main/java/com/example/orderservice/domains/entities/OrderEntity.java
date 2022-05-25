package com.example.orderservice.domains.entities;

import com.example.orderservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "orders", schema = "store")
public class OrderEntity extends BaseEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Column(nullable = false)
    private BigDecimal value;

    @OneToMany(mappedBy = "id")
    private List<ProductEntity> productsList;

    @Column(nullable = false)
    private LocalDateTime buyingDate;

}
