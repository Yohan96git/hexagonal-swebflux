package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApplicantUpdateRequest {

    private String phone;

    @NotBlank(message="This value must not be empty or null")
    private String email;

    @NotBlank(message="This value must not be empty or null")
    private String cvUrl;
}
