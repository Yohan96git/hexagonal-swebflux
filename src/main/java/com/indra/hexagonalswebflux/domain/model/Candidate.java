package com.indra.hexagonalswebflux.domain.model;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {

    private String id;
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

}
