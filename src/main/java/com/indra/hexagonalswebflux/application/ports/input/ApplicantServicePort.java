package com.indra.hexagonalswebflux.application.ports.input;

import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplicantServicePort {

    Mono<Applicant> findByDni(String dni);

    Flux<Applicant> findAll();

    Mono<Applicant> save(Applicant candidate);

    Mono<Applicant> update(String dni, Applicant candidate);

    Mono<Void> deleteByDni(String dni);

}
