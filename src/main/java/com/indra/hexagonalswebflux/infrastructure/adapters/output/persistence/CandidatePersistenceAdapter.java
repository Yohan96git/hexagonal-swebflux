package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence;

import com.indra.hexagonalswebflux.application.ports.output.CandidatePersistencePort;
import com.indra.hexagonalswebflux.domain.exception.APIException;
import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.CandidateDocument;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.mapper.CandidatePersistenceMapper;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.repository.CandidateRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CandidatePersistenceAdapter implements CandidatePersistencePort {

    private final CandidateRepository repository;

    private static final String FALLBACK_METHOD_MONO = "fallbackMono";
    private static final String FALLBACK_METHOD_MONO_EVALUATION = "EvaluationfallbackMono";
    private static final String FALLBACK_METHOD_FLUX = "fallbackFlux";

    @CircuitBreaker(name = "findByDni", fallbackMethod = FALLBACK_METHOD_MONO)
    @Override
    public Mono<Candidate> findByDni(String dni) {
        Mono<CandidateDocument> candidateMono = repository.findByDni(dni);
        return candidateMono.flatMap(candidateDocument -> Mono.just(CandidatePersistenceMapper.ToCandidate(candidateDocument)));
    }

    @CircuitBreaker(name = "findAll", fallbackMethod = FALLBACK_METHOD_FLUX)
    @Override
    public Flux<Candidate> findAll() {
        return repository.findAll().map(CandidatePersistenceMapper::ToCandidate);
    }

    @CircuitBreaker(name = "save", fallbackMethod = FALLBACK_METHOD_MONO)
    @Override
    public Mono<Candidate> save(Candidate candidate) {
        return repository.save(CandidatePersistenceMapper.ToCandidateDocument(candidate)).map(CandidatePersistenceMapper::ToCandidate);
    }

    @CircuitBreaker(name = "saveEvaluation", fallbackMethod = FALLBACK_METHOD_MONO_EVALUATION)
    @Override
    public Mono<Evaluation> saveEvaluation(Evaluation evaluation) {
        return repository.save(CandidatePersistenceMapper.evaluationToCandidate(evaluation)).map(CandidatePersistenceMapper::documentToEvaluation);
    }

    @CircuitBreaker(name = "deleteById", fallbackMethod = FALLBACK_METHOD_MONO)
    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    public Mono<Applicant> fallbackMono(Exception ex) {
        return Mono.error(new APIException("Error en la comunicación con la base de datos"));
    }

    public Flux<Applicant> fallbackFlux(Exception ex) {
        return Flux.error(new APIException("Error en la comunicación con la base de datos"));
    }

    public Flux<Evaluation> EvaluationfallbackFlux(Exception ex) {
        return Flux.error(new APIException("Error en la comunicación con la base de datos"));
    }
}
