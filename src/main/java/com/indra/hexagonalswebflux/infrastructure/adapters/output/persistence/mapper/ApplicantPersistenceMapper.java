package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.mapper;

import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.ApplicantUpdateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.ApplicantDocument;

import java.util.Objects;

public class ApplicantPersistenceMapper {

    public static ApplicantDocument ToApplicantDocument(Applicant applicant){
        ApplicantDocument document = new ApplicantDocument();
        if(Objects.nonNull(applicant)){
            document = ApplicantDocument.builder()
                    .id(applicant.getId())
                    .dni(applicant.getDni())
                    .name(applicant.getName())
                    .lastName(applicant.getLastName())
                    .phone(applicant.getPhone())
                    .email(applicant.getEmail())
                    .cvUrl(applicant.getCvUrl())
                    .lastEvaluation(applicant.getLastEvaluation())
                    .build();
        }

        return document;
    }

    public static Applicant ToApplicant(ApplicantDocument request){
        Applicant applicant = new Applicant();
        if(Objects.nonNull(request)){
            applicant = Applicant.builder()
                    .id(request.getId())
                    .dni(request.getDni())
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .cvUrl(request.getCvUrl())
                    .build();
        }

        return applicant;
    }
}
