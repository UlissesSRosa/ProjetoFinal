package com.example.orderservice.domains.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class OrderDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private Long userId;

    @NotBlank
    private BigDecimal value;

    @NotBlank
    private List<ProductDTO> productsList;

    @NotBlank
    private LocalDateTime buyingDate;

}
