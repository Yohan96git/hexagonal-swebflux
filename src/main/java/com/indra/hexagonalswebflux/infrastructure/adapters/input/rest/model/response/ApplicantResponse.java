package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {

    private String dni;
    private String name;
    private String lastName;
    private String phone;
    private String email;

}
