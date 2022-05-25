package com.example.orderservice.domains.entities;

import com.example.orderservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "promotions", schema = "store")
public class PromotionEntity extends BaseEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal percent;

    @Column(nullable = false)
    private Boolean isActive;
}