package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Setter
@Builder
@Document(collection = "candidate")
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDocument {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String address;
    private String dni;
    private String phone;
    private String email;
    private String cvUrl;
    private Integer yearExp;
    private String softwareKnowledge;
    private String profileUrl;


}
