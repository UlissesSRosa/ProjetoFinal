package com.example.managementservice.domains.dtos.requests;

import com.example.managementservice.domains.dtos.ProductDTO;
import com.example.managementservice.domains.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ShoppingCartRequestDTO {

    @NotBlank
    private Long productId;

    @NotBlank
    private Long userId;

    @NotBlank
    private OrderStatus status;

    @NotBlank
    private Long payableAmount;

    @NotBlank
    private Long totalAmount;
}
