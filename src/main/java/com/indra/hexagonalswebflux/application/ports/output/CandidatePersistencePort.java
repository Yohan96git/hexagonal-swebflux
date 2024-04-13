package com.indra.hexagonalswebflux.application.ports.output;

import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface CandidatePersistencePort {

    Mono<Candidate> findByDni(String dni);

    Flux<Candidate> findAll();

    Mono<Candidate> save(Candidate candidate);

    Mono<Evaluation> saveEvaluation(Evaluation evaluation);

    Mono<Void> deleteById(String id);

}
