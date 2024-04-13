package com.indra.hexagonalswebflux.application.ports.output;

import com.indra.hexagonalswebflux.domain.model.Applicant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface ApplicantPersistencePort {

    Mono<Applicant> findByDni(String dni);

    Flux<Applicant> findAll();

    Mono<Applicant> save(Applicant applicant);

    Mono<Void> deleteById(String id);

}
