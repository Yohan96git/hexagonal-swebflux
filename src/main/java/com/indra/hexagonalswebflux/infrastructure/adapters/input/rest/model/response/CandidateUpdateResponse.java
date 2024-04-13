package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateUpdateResponse {

    private String dni;
    private String address;
    private String phone;
    private String email;
    private String cvUrl;
    private Integer yearExp;
    private String softwareKnowledge;
    private String profileUrl;

}
