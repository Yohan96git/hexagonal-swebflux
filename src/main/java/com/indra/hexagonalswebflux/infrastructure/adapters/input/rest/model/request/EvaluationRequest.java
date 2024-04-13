package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class EvaluationRequest {

    private String dni;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String cvUrl;
    private Integer yearExp;
    private String softwareKnowledge;
    private String profileUrl;
    private Boolean isApproved;

}
