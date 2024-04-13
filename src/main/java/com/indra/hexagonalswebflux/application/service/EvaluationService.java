package com.indra.hexagonalswebflux.application.service;

import com.indra.hexagonalswebflux.application.ports.input.EvaluationServicePort;
import com.indra.hexagonalswebflux.application.ports.output.ApplicantPersistencePort;
import com.indra.hexagonalswebflux.application.ports.output.CandidatePersistencePort;
import com.indra.hexagonalswebflux.domain.exception.APIException;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EvaluationService implements EvaluationServicePort {

    private final ApplicantPersistencePort applicantPersistencePort;

    private final CandidatePersistencePort candidatePersistencePort;


    @Override
    public Mono<Evaluation> save(Evaluation evaluation) {

        return applicantPersistencePort.findByDni(evaluation.getDni())
                .switchIfEmpty(Mono.error(new APIException("Applicant not found")))
                .flatMap(applicant -> {
                    if(evaluation.getIsApproved()){
                        return candidatePersistencePort.saveEvaluation(evaluation);
                    }else{
                        applicant.setLastEvaluation(new Date());
                        return applicantPersistencePort.save(applicant).map(a -> {
                            evaluation.setId(a.getId());
                            return evaluation;
                        });
                    }
                });
    }
}
