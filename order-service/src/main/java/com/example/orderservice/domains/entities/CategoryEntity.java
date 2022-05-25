package com.example.orderservice.domains.entities;

import com.example.orderservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "categories", schema = "store", uniqueConstraints = { @UniqueConstraint(columnNames = {"name"})})
public class CategoryEntity extends BaseEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_promotion")
    private PromotionEntity promotion;
}