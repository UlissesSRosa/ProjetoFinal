package com.example.managementservice.domains.entities;

import com.example.managementservice.domains.models.BaseEntityModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "products", schema = "store", uniqueConstraints = { @UniqueConstraint(columnNames = {"name", "categoryId"})})
public class ProductEntity extends BaseEntityModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String url_image;

    @Column(nullable = false)
    private Long weigth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private CategoryEntity categoryId;

}
