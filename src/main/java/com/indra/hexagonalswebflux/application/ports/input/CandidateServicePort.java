package com.indra.hexagonalswebflux.application.ports.input;

import com.indra.hexagonalswebflux.domain.model.Candidate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CandidateServicePort {

    Mono<Candidate> findByDni(String dni);

    Flux<Candidate> findAll();

    Mono<Candidate> save(Candidate candidate);

    Mono<Candidate> update(String dni, Candidate candidate);

    Mono<Void> deleteByDni(String dni);

}
