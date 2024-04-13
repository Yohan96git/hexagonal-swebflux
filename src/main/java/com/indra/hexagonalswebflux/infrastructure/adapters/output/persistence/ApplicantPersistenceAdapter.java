package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence;

import com.indra.hexagonalswebflux.application.ports.output.ApplicantPersistencePort;
import com.indra.hexagonalswebflux.domain.exception.APIException;
import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.ApplicantDocument;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.mapper.ApplicantPersistenceMapper;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.repository.ApplicantRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.netty.handler.codec.http2.HttpConversionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
@RequiredArgsConstructor
public class ApplicantPersistenceAdapter implements ApplicantPersistencePort {

    private final ApplicantRepository repository;
    private static final String FALLBACK_METHOD_MONO = "fallbackMono";
    private static final String FALLBACK_METHOD_FLUX = "fallbackFlux";


    @CircuitBreaker(name = "findByDni", fallbackMethod = FALLBACK_METHOD_MONO)
    @Override
    public Mono<Applicant> findByDni(String dni) {
        Mono<ApplicantDocument> applicantMono = repository.findByDni(dni);
        return applicantMono.flatMap(applicantDocument -> Mono.just(ApplicantPersistenceMapper.ToApplicant(applicantDocument)));
    }

    @CircuitBreaker(name = "findAll", fallbackMethod = FALLBACK_METHOD_FLUX)
    @Override
    public Flux<Applicant> findAll() {
        return repository.findAll().map(ApplicantPersistenceMapper::ToApplicant);
    }

    @CircuitBreaker(name = "save", fallbackMethod = FALLBACK_METHOD_MONO)
    @Override
    public Mono<Applicant> save(Applicant applicant) {
        return repository.save(ApplicantPersistenceMapper.ToApplicantDocument(applicant)).map(ApplicantPersistenceMapper::ToApplicant);
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
}
