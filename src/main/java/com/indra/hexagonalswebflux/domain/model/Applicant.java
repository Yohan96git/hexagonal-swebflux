package com.indra.hexagonalswebflux.domain.model;

import lombok.*;

import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {

    private String id;
    private String dni;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String cvUrl;
    private Date lastEvaluation;
}
