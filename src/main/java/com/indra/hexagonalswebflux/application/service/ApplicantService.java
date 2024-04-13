package com.indra.hexagonalswebflux.application.service;

import com.indra.hexagonalswebflux.application.ports.input.ApplicantServicePort;
import com.indra.hexagonalswebflux.application.ports.output.ApplicantPersistencePort;
import com.indra.hexagonalswebflux.domain.exception.APIException;
import com.indra.hexagonalswebflux.domain.model.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ApplicantService implements ApplicantServicePort {

    private final ApplicantPersistencePort persistencePort;

    @Override
    public Mono<Applicant> findByDni(String dni) {
        return persistencePort.findByDni(dni).switchIfEmpty(Mono.error(new APIException("Applicant not found")));
    }

    @Override
    public Flux<Applicant> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public Mono<Applicant> save(Applicant applicant) {
        return persistencePort.findByDni(applicant.getDni())
                .switchIfEmpty(persistencePort.save(applicant));
    }

    @Override
    public Mono<Applicant> update(String dni, Applicant applicant) {
        return persistencePort.findByDni(dni)
                .switchIfEmpty(Mono.error(new APIException("Applicant not found")))
                .flatMap(a -> {
                    applicant.setId(a.getId());
                    applicant.setDni(a.getDni());
                    applicant.setName(a.getName());
                    applicant.setLastName(a.getLastName());
                    applicant.setLastEvaluation(applicant.getLastEvaluation());
                    return persistencePort.save(applicant);
                });
    }

    @Override
    public Mono<Void> deleteByDni(String dni) {
        return persistencePort.findByDni(dni)
                .switchIfEmpty(Mono.error(new APIException("Applicant not found")))
                .flatMap(applicant -> persistencePort.deleteById(applicant.getId()));
    }
}
