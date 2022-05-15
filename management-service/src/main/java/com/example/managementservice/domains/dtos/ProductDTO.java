package com.example.managementservice.domains.dtos;

import com.example.managementservice.domains.entities.CategoryEntity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ProductDTO {

    private String name;
    private String description;
    private Long price;
    private int stock;
    private String url_image;
    private Long weigth;
    private Long categoryId;
}
