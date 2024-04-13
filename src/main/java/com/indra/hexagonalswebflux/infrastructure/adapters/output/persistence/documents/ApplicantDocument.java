package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@Document(collection = "applicant")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDocument {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private String email;
    private String cvUrl;
    private Date lastEvaluation;

}
