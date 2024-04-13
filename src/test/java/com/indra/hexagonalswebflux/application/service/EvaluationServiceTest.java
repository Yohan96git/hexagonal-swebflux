package com.indra.hexagonalswebflux.application.service;

import com.indra.hexagonalswebflux.application.ports.output.ApplicantPersistencePort;
import com.indra.hexagonalswebflux.application.ports.output.CandidatePersistencePort;
import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EvaluationServiceTest {

    @InjectMocks
    private EvaluationService evaluationService;

    @Mock
    private CandidatePersistencePort candidatePersistencePort;

    @Mock
    private ApplicantPersistencePort applicantPersistencePort;

    @Test
    public void saveTest() {
        Evaluation evaluation = Evaluation
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .address("address test")
                .profileUrl("profile.com")
                .softwareKnowledge("programacion")
                .yearExp(5)
                .isApproved(true)
                .build();

        Mockito.lenient().when(applicantPersistencePort.findByDni(evaluation.getDni())).thenReturn(Mono.just(Applicant.builder().build()));
        Mockito.lenient().when(candidatePersistencePort.saveEvaluation(any())).thenReturn(Mono.just(evaluation));

        evaluationService.save(evaluation);

    }
}
