package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.mapper;

import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.EvaluationRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.CandidateResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.EvaluationResponse;

import java.util.Objects;

public class EvaluationMapper {

    public static Evaluation CreateRequestToEvaluation(EvaluationRequest request){
        Evaluation evaluation = new Evaluation();
        if(Objects.nonNull(request)){
            evaluation = Evaluation.builder()
                    .dni(request.getDni())
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .cvUrl(request.getCvUrl())
                    .profileUrl(request.getProfileUrl())
                    .softwareKnowledge(request.getSoftwareKnowledge())
                    .yearExp(request.getYearExp())
                    .address(request.getAddress())
                    .isApproved(request.getIsApproved())
                    .build();
        }

        return evaluation;
    }

    public static EvaluationResponse EvaluationToResponse(Evaluation evaluation){
        EvaluationResponse response = new EvaluationResponse();
        if(Objects.nonNull(evaluation)){
            response = EvaluationResponse.builder()
                    .dni(evaluation.getDni())
                    .name(evaluation.getName())
                    .lastName(evaluation.getLastName())
                    .build();
        }

        return response;
    }

}
