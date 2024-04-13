package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantCreateResponse {

    private String dni;
    private String name;
    private String lastName;

}
