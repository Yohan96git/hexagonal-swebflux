package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.repository;

import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.ApplicantDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ApplicantRepositoryTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    public void findByDniTest() {
        Mono<ApplicantDocument> applicantDocumentFlux = applicantRepository.save(ApplicantDocument.builder()
                .dni("12350")
                .name("Name test a")
                .lastName("lastName test a")
                .email("Email test")
                .phone("98766")
                .cvUrl("cv")
                .build()).flatMap(a->applicantRepository.findByDni("12350"));

        StepVerifier
                .create(applicantDocumentFlux)
                .assertNext(candidate -> {
                    assertEquals("Name test a", candidate.getName());
                    assertEquals("lastName test a", candidate.getLastName());
                    assertNotNull(candidate.getEmail());
                })
                .expectComplete()
                .verify();
    }

}
