package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationResponse {

    private String dni;
    private String name;
    private String lastName;

}
