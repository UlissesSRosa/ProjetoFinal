package com.example.managementservice.domains.dtos;

import com.example.managementservice.domains.dtos.requests.PromotionRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CategoryDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    private Long promotionId;
}
