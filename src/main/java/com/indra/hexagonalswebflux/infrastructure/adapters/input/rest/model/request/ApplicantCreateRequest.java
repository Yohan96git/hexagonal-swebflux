package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class ApplicantCreateRequest {

    @NotBlank(message="This value must not be empty or null")
    private String dni;

    @NotBlank(message="This value must not be empty or null")
    private String name;

    @NotBlank(message="This value must not be empty or null")
    private String lastName;

    private String phone;

    @NotBlank(message="This value must not be empty or null")
    private String email;

    @NotBlank(message="This value must not be empty or null")
    private String cvUrl;

}
