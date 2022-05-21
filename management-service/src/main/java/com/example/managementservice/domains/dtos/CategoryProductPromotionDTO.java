package com.example.managementservice.domains.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CategoryProductPromotionDTO {


    private String productName;
    private String urlImage;
    private BigDecimal originalPrice;
    private BigDecimal promotionalPrice;

}
