package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.repository;

import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.CandidateDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class CandidateRepositoryTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void findByDniTest() {
        Mono<CandidateDocument> candidateDocumentFlux = candidateRepository.save(CandidateDocument.builder()
                .name("Name test")
                .lastName("lastName test")
                .dni("12346")
                .email("Email test")
                .softwareKnowledge("Knowledge test")
                .address("Address test")
                .phone("98766")
                .cvUrl("cv")
                .build()).flatMap(a->candidateRepository.findByDni("12346"));

        StepVerifier
                .create(candidateDocumentFlux)
                .assertNext(candidate -> {
                    assertEquals("Name test", candidate.getName());
                    assertEquals("lastName test", candidate.getLastName());
                    assertNotNull(candidate.getEmail());
                })
                .expectComplete()
                .verify();
    }

}
