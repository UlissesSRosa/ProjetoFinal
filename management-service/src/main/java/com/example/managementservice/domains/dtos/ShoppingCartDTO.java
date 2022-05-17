package com.example.managementservice.domains.dtos;

import com.example.managementservice.domains.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
    private Long payableAmount;

    @NotBlank
    private Long totalAmount;

    @NotBlank
    private Long userId;

}
