package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantUpdateResponse {

    private String dni;
    private String phone;
    private String email;
    private String cvUrl;

}
