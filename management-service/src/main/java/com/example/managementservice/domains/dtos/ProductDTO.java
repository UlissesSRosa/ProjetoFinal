package com.example.managementservice.domains.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ProductDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Long price;

    @NotBlank
    private int stock;

    @NotBlank
    private String url_image;

    @NotBlank
    private Long weigth;

    @NotBlank
    private Long categoryId;
}
