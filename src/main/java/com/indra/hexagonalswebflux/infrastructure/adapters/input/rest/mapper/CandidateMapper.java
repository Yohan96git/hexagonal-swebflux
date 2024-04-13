package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.mapper;

import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.CandidateCreateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.CandidateUpdateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.CandidateCreateResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.CandidateResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.CandidateUpdateResponse;

import java.util.Objects;

public class CandidateMapper {

    public static Candidate CreateRequestToCandidate(CandidateCreateRequest request){
        Candidate candidate = new Candidate();
        if(Objects.nonNull(request)){
            candidate = Candidate.builder()
                    .dni(request.getDni())
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .cvUrl(request.getCvUrl())
                    .address(request.getAddress())
                    .yearExp(request.getYearExp())
                    .softwareKnowledge(request.getSoftwareKnowledge())
                    .profileUrl(request.getProfileUrl())
                    .build();
        }

        return candidate;
    }

    public static CandidateCreateResponse CandidateToCreateResponse(Candidate candidate){
        CandidateCreateResponse response = new CandidateCreateResponse();
        if(Objects.nonNull(candidate)){
            response = CandidateCreateResponse.builder()
                    .dni(candidate.getDni())
                    .name(candidate.getName())
                    .lastName(candidate.getLastName())
                    .profileUrl(candidate.getProfileUrl())
                    .address(candidate.getAddress())
                    .yearExp(candidate.getYearExp())
                    .softwareKnowledge(candidate.getSoftwareKnowledge())
                    .phone(candidate.getPhone())
                    .email(candidate.getEmail())
                    .cvUrl(candidate.getCvUrl())
                    .build();
        }

        return response;
    }

    public static Candidate UpdateRequestToCandidate(CandidateUpdateRequest request){
        Candidate candidate = new Candidate();
        if(Objects.nonNull(request)){
            candidate = Candidate.builder()
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .cvUrl(request.getCvUrl())
                    .profileUrl(request.getProfileUrl())
                    .softwareKnowledge(request.getSoftwareKnowledge())
                    .address(request.getAddress())
                    .build();
        }

        return candidate;
    }

    public static CandidateUpdateResponse CandidateToUpdateResponse(Candidate candidate){
        CandidateUpdateResponse response = new CandidateUpdateResponse();
        if(Objects.nonNull(candidate)){
            response = CandidateUpdateResponse.builder()
                    .dni(candidate.getDni())
                    .phone(candidate.getPhone())
                    .email(candidate.getEmail())
                    .cvUrl(candidate.getCvUrl())
                    .address(candidate.getAddress())
                    .softwareKnowledge(candidate.getSoftwareKnowledge())
                    .yearExp(candidate.getYearExp())
                    .profileUrl(candidate.getProfileUrl())
                    .build();
        }

        return response;
    }

    public static CandidateResponse CandidateToResponse(Candidate candidate){
        CandidateResponse response = new CandidateResponse();
        if(Objects.nonNull(candidate)){
            response = CandidateResponse.builder()
                    .dni(candidate.getDni())
                    .name(candidate.getName())
                    .lastName(candidate.getLastName())
                    .phone(candidate.getPhone())
                    .email(candidate.getEmail())
                    .cvUrl(candidate.getCvUrl())
                    .profileUrl(candidate.getProfileUrl())
                    .address(candidate.getAddress())
                    .yearExp(candidate.getYearExp())
                    .softwareKnowledge(candidate.getSoftwareKnowledge())
                    .build();
        }

        return response;
    }

}
