package com.indra.hexagonalswebflux.application.service;

import com.indra.hexagonalswebflux.application.ports.output.CandidatePersistencePort;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceTest {

    @InjectMocks
    private CandidateService candidateService;

    @Mock
    private CandidatePersistencePort candidatePersistencePort;

    @Test
    public void findByDniTest() {
        Candidate candidate = Candidate
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .address("address test")
                .profileUrl("profile.com")
                .softwareKnowledge("programacion")
                .yearExp(5)
                .build();

        when(candidatePersistencePort.findByDni(candidate.getDni())).thenReturn(Mono.just(candidate));
        Mono<Candidate> candidateMono = candidateService.findByDni(candidate.getDni());
        StepVerifier
                .create(candidateMono)
                .consumeNextWith(candidateResponse -> {
                            Assertions.assertThat(candidateResponse.getName()).isEqualTo("nombre test");
                            Assertions.assertThat(candidateResponse.getLastName()).isEqualTo("last name");
                            Assertions.assertThat(candidateResponse.getDni()).isEqualTo("7654321");
                            Assertions.assertThat(candidateResponse.getEmail()).isEqualTo("email@com");
                            Assertions.assertThat(candidateResponse.getCvUrl()).isEqualTo("cv.com");
                            Assertions.assertThat(candidateResponse.getAddress()).isEqualTo("address test");
                            Assertions.assertThat(candidateResponse.getProfileUrl()).isEqualTo("profile.com");
                            Assertions.assertThat(candidateResponse.getSoftwareKnowledge()).isEqualTo("programacion");               })
                .verifyComplete();
    }

    @Test
    public void findAll() {
        Candidate candidate1 = Candidate.builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .address("address test")
                .profileUrl("profile.com")
                .softwareKnowledge("programacion")
                .yearExp(5)
                .build();

        Candidate candidate2 = Candidate.builder()
                .name("nombre test 2")
                .lastName("last name 2")
                .dni("7654321 2")
                .email("email@com 2")
                .phone("987654321 2")
                .cvUrl("cv.com 2")
                .address("address test 2")
                .profileUrl("profile.com 2")
                .softwareKnowledge("programacion 2")
                .yearExp(10)
                .build();

        when(candidatePersistencePort.findAll()).thenReturn(Flux.just(candidate1, candidate2));
        Flux<Candidate> candidateFlux = candidateService.findAll();
        StepVerifier
                .create(candidateFlux)
                .assertNext(response -> {
                    Assertions.assertThat(response.getName()).isEqualTo("nombre test");
                    Assertions.assertThat(response.getLastName()).isEqualTo("last name");
                    Assertions.assertThat(response.getDni()).isEqualTo("7654321");
                    Assertions.assertThat(response.getEmail()).isEqualTo("email@com");
                })
                .assertNext(response -> {
                    Assertions.assertThat(response.getName()).isEqualTo("nombre test 2");
                    Assertions.assertThat(response.getLastName()).isEqualTo("last name 2");
                    Assertions.assertThat(response.getDni()).isEqualTo("7654321 2");
                    Assertions.assertThat(response.getEmail()).isEqualTo("email@com 2");
                })
                .verifyComplete();
    }

    @Test
    public void saveTest() {
        Candidate candidate = Candidate
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .address("address test")
                .profileUrl("profile.com")
                .softwareKnowledge("programacion")
                .yearExp(5)
                .build();

        when(candidatePersistencePort.findByDni(candidate.getDni())).thenReturn(Mono.empty());
        when(candidatePersistencePort.save(candidate)).thenReturn(Mono.just(candidate));

        Mono<Candidate> candidateMono = candidateService.save(candidate);
        StepVerifier
                .create(candidateMono)
                .consumeNextWith(response -> {
                    Assertions.assertThat(response.getName()).isEqualTo("nombre test");
                    Assertions.assertThat(response.getLastName()).isEqualTo("last name");
                    Assertions.assertThat(response.getDni()).isEqualTo("7654321");
                    Assertions.assertThat(response.getEmail()).isEqualTo("email@com");                })
                .verifyComplete();
    }

    @Test
    public void updateTest() {
        Candidate candidate = Candidate
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .address("address test")
                .profileUrl("profile.com")
                .softwareKnowledge("programacion")
                .yearExp(5)
                .build();

        when(candidatePersistencePort.findByDni(candidate.getDni())).thenReturn(Mono.just(candidate));
        when(candidatePersistencePort.save(candidate)).thenReturn(Mono.just(candidate));

        Mono<Candidate> candidateMono = candidateService.save(candidate);
        StepVerifier
                .create(candidateMono)
                .consumeNextWith(response -> {
                    Assertions.assertThat(response.getName()).isEqualTo("nombre test");
                    Assertions.assertThat(response.getLastName()).isEqualTo("last name");
                    Assertions.assertThat(response.getDni()).isEqualTo("7654321");
                    Assertions.assertThat(response.getEmail()).isEqualTo("email@com");                  })
                .verifyComplete();
    }
}
