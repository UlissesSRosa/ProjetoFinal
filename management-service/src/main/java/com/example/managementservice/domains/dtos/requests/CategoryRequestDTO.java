package com.example.managementservice.domains.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CategoryRequestDTO {

    @NotBlank
    private String name;

    private Long promotionId;

}
