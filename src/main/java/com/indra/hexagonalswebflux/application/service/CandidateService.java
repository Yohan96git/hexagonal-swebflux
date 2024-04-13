package com.indra.hexagonalswebflux.application.service;

import com.indra.hexagonalswebflux.application.ports.input.CandidateServicePort;
import com.indra.hexagonalswebflux.application.ports.output.CandidatePersistencePort;
import com.indra.hexagonalswebflux.domain.exception.APIException;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CandidateService implements CandidateServicePort {

    private final CandidatePersistencePort persistencePort;

    @Override
    public Mono<Candidate> findByDni(String dni) {
        return persistencePort.findByDni(dni).switchIfEmpty(Mono.error(new APIException("Candidate not found")));
    }

    @Override
    public Flux<Candidate> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public Mono<Candidate> save(Candidate candidate) {
        return persistencePort.findByDni(candidate.getDni())
                .switchIfEmpty(persistencePort.save(candidate));
    }

    @Override
    public Mono<Candidate> update(String dni, Candidate candidate) {
        return persistencePort.findByDni(dni)
                .switchIfEmpty(Mono.error(new APIException("Candidate not found")))
                .flatMap(a -> {
                    candidate.setId(a.getId());
                    candidate.setDni(a.getDni());
                    candidate.setName(a.getName());
                    candidate.setLastName(a.getLastName());
                    return persistencePort.save(candidate);
                });
    }

    @Override
    public Mono<Void> deleteByDni(String dni) {
        return persistencePort.findByDni(dni)
                .switchIfEmpty(Mono.error(new APIException("Candidate not found")))
                .flatMap(candidate -> persistencePort.deleteById(candidate.getId()));
    }
}
