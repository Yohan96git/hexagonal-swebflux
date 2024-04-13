package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import com.indra.hexagonalswebflux.application.ports.input.CandidateServicePort;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.CandidateCreateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.CandidateUpdateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CandidateRouter.class, CandidateHandler.class})
@WebFluxTest
public class CandidateHandlerTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private CandidateServicePort candidateServicePort;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void getCandidateByDniTest() {

        Mono<Candidate> candidateMono = Mono.just(Candidate
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
                .build());

        when(candidateServicePort.findByDni("7654321")).thenReturn(candidateMono);
        webTestClient.get()
                .uri("/candidates/7654321")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CandidateResponse.class)
                .value(candidateResponse -> {
                            Assertions.assertThat(candidateResponse.getName()).isEqualTo("nombre test");
                            Assertions.assertThat(candidateResponse.getLastName()).isEqualTo("last name");
                            Assertions.assertThat(candidateResponse.getDni()).isEqualTo("7654321");
                            Assertions.assertThat(candidateResponse.getEmail()).isEqualTo("email@com");
                            Assertions.assertThat(candidateResponse.getCvUrl()).isEqualTo("cv.com");
                            Assertions.assertThat(candidateResponse.getAddress()).isEqualTo("address test");
                            Assertions.assertThat(candidateResponse.getProfileUrl()).isEqualTo("profile.com");
                            Assertions.assertThat(candidateResponse.getSoftwareKnowledge()).isEqualTo("programacion");
                        }
                );
    }


    @Test
    public void findAllTest() {

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

        when(candidateServicePort.findAll()).thenReturn(Flux.just(candidate1, candidate2));

        webTestClient.get()
                .uri("/candidates")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ApplicantResponse.class)
                .value(candidateResponse -> {
                            Assertions.assertThat(candidateResponse.get(0).getName()).isEqualTo("nombre test");
                            Assertions.assertThat(candidateResponse.get(0).getLastName()).isEqualTo("last name");
                            Assertions.assertThat(candidateResponse.get(0).getDni()).isEqualTo("7654321");
                            Assertions.assertThat(candidateResponse.get(0).getEmail()).isEqualTo("email@com");

                            Assertions.assertThat(candidateResponse.get(1).getName()).isEqualTo("nombre test 2");
                            Assertions.assertThat(candidateResponse.get(1).getLastName()).isEqualTo("last name 2");
                            Assertions.assertThat(candidateResponse.get(1).getDni()).isEqualTo("7654321 2");
                            Assertions.assertThat(candidateResponse.get(1).getEmail()).isEqualTo("email@com 2");
                        }
                );
    }


    @Test
    public void saveTest() {

        Mono<Candidate> candidateMono = Mono.just(Candidate
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
                .build());

        when(candidateServicePort.save(any())).thenReturn(candidateMono);

        webTestClient.post()
                .uri("/candidates")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(CandidateCreateRequest.builder().build()), CandidateCreateRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CandidateCreateResponse.class)
                .value(candidateResponse -> {
                            Assertions.assertThat(candidateResponse.getName()).isEqualTo("nombre test");
                            Assertions.assertThat(candidateResponse.getLastName()).isEqualTo("last name");
                            Assertions.assertThat(candidateResponse.getDni()).isEqualTo("7654321");
                            Assertions.assertThat(candidateResponse.getEmail()).isEqualTo("email@com");
                        }
                );

    }

    @Test
    public void updateTest() {

        Mono<Candidate> candidateMono = Mono.just(Candidate
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
                .build());

        when(candidateServicePort.update(any(), any())).thenReturn(candidateMono);

        webTestClient.put()
                .uri("/candidates/7654321")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(CandidateUpdateRequest.builder().build()), CandidateUpdateRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CandidateUpdateResponse.class)
                .value(response -> {
                            Assertions.assertThat(response.getPhone()).isEqualTo("987654321");
                            Assertions.assertThat(response.getEmail()).isEqualTo("email@com");
                        }
                );

    }
}
