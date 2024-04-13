package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.repository;

import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.ApplicantDocument;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.CandidateDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CandidateRepository extends ReactiveMongoRepository<CandidateDocument, String> {

    Mono<CandidateDocument> findByDni(String dni);

}