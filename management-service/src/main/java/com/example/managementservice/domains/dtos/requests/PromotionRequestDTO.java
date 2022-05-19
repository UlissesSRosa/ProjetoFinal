package com.example.managementservice.domains.dtos.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PromotionRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private Long percent;

    @NotBlank
    private Boolean isActive;
}
