package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.mapper;

import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.CandidateDocument;

import java.util.Objects;

public class CandidatePersistenceMapper {

    public static CandidateDocument ToCandidateDocument(Candidate candidate) {
        CandidateDocument document = new CandidateDocument();
        if (Objects.nonNull(candidate)) {
            document = CandidateDocument.builder()
                    .dni(candidate.getDni())
                    .name(candidate.getName())
                    .lastName(candidate.getLastName())
                    .phone(candidate.getPhone())
                    .email(candidate.getEmail())
                    .cvUrl(candidate.getCvUrl())
                    .id(candidate.getId())
                    .address(candidate.getAddress())
                    .yearExp(candidate.getYearExp())
                    .profileUrl(candidate.getProfileUrl())
                    .softwareKnowledge(candidate.getSoftwareKnowledge())
                    .build();
        }

        return document;
    }

    public static Candidate ToCandidate(CandidateDocument document) {
        Candidate candidate = new Candidate();
        if (Objects.nonNull(document)) {
            candidate = Candidate.builder()
                    .id(document.getId())
                    .dni(document.getDni())
                    .name(document.getName())
                    .lastName(document.getLastName())
                    .phone(document.getPhone())
                    .email(document.getEmail())
                    .cvUrl(document.getCvUrl())
                    .address(document.getAddress())
                    .profileUrl(document.getProfileUrl())
                    .yearExp(document.getYearExp())
                    .softwareKnowledge(document.getSoftwareKnowledge())
                    .build();
        }

        return candidate;
    }


    public static CandidateDocument evaluationToCandidate(Evaluation evaluation) {
        CandidateDocument document = new CandidateDocument();
        if (Objects.nonNull(evaluation)) {
            document = CandidateDocument.builder()
                    .dni(evaluation.getDni())
                    .name(evaluation.getName())
                    .lastName(evaluation.getLastName())
                    .phone(evaluation.getPhone())
                    .email(evaluation.getEmail())
                    .cvUrl(evaluation.getCvUrl())
                    .id(evaluation.getId())
                    .address(evaluation.getAddress())
                    .yearExp(evaluation.getYearExp())
                    .profileUrl(evaluation.getProfileUrl())
                    .softwareKnowledge(evaluation.getSoftwareKnowledge())
                    .build();
        }
        return document;
    }

    public static Evaluation documentToEvaluation(CandidateDocument document) {
        Evaluation evaluation = new Evaluation();
        if (Objects.nonNull(document)) {
            evaluation = Evaluation.builder()
                    .id(document.getId())
                    .dni(document.getDni())
                    .name(document.getName())
                    .lastName(document.getLastName())
                    .phone(document.getPhone())
                    .email(document.getEmail())
                    .cvUrl(document.getCvUrl())
                    .address(document.getAddress())
                    .yearExp(document.getYearExp())
                    .profileUrl(document.getProfileUrl())
                    .softwareKnowledge(document.getSoftwareKnowledge())
                    .build();
        }

        return evaluation;
    }

}
