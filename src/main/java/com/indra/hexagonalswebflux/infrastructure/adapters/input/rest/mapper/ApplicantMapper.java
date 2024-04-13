package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.mapper;

import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.ApplicantCreateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.ApplicantUpdateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.EvaluationRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantCreateResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantUpdateResponse;

import java.util.Objects;

public class ApplicantMapper {

    public static Applicant CreateRequestToApplicant(ApplicantCreateRequest request){
        Applicant applicant = new Applicant();
        if(Objects.nonNull(request)){
            applicant = Applicant.builder()
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

    public static ApplicantCreateResponse ApplicantToCreateResponse(Applicant applicant){
        ApplicantCreateResponse response = new ApplicantCreateResponse();
        if(Objects.nonNull(applicant)){
            response = ApplicantCreateResponse.builder()
                    .dni(applicant.getDni())
                    .name(applicant.getName())
                    .lastName(applicant.getLastName())
                    .build();
        }

        return response;
    }

    public static Applicant UpdateRequestToApplicant(ApplicantUpdateRequest request){
        Applicant applicant = new Applicant();
        if(Objects.nonNull(request)){
            applicant = Applicant.builder()
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .cvUrl(request.getCvUrl())
                    .build();
        }

        return applicant;
    }

    public static ApplicantUpdateResponse ApplicantToUpdateResponse(Applicant applicant){
        ApplicantUpdateResponse response = new ApplicantUpdateResponse();
        if(Objects.nonNull(applicant)){
            response = ApplicantUpdateResponse.builder()
                    .dni(applicant.getDni())
                    .phone(applicant.getPhone())
                    .email(applicant.getEmail())
                    .cvUrl(applicant.getCvUrl())
                    .build();
        }

        return response;
    }

    public static ApplicantResponse ApplicantToResponse(Applicant applicant){
        ApplicantResponse response = new ApplicantResponse();
        if(Objects.nonNull(applicant)){
            response = ApplicantResponse.builder()
                    .dni(applicant.getDni())
                    .name(applicant.getName())
                    .lastName(applicant.getLastName())
                    .phone(applicant.getPhone())
                    .email(applicant.getEmail())
                    .build();
        }

        return response;
    }
}
