package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.repository;

import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.ApplicantDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ApplicantRepository extends ReactiveMongoRepository<ApplicantDocument, String> {

    Mono<ApplicantDocument> findByDni(String dni);

}