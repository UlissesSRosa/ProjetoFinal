package com.example.orderservice.domains.dtos;

import com.example.orderservice.domains.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ShoppingCartDTO {

    @NotBlank
    private Long cartId;

    @NotBlank
    private List<ProductDTO> products;

    @NotBlank
    private OrderStatus status;

    @NotBlank
    private BigDecimal payableAmount;

    @NotBlank
    private BigDecimal totalAmount;

    @NotBlank
    private Long userId;

}
