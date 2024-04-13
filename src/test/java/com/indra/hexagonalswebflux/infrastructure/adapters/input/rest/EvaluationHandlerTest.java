package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import com.indra.hexagonalswebflux.application.ports.input.EvaluationServicePort;
import com.indra.hexagonalswebflux.domain.model.Evaluation;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.EvaluationRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.EvaluationResponse;
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
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EvaluationRouter.class, EvaluationHandler.class})
@WebFluxTest
public class EvaluationHandlerTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private EvaluationServicePort evaluationServicePort;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void saveTest() {

        Mono<Evaluation> evaluationMono = Mono.just(Evaluation
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
                .isApproved(true)
                .build());

        when(evaluationServicePort.save(any())).thenReturn(evaluationMono);

        webTestClient.post()
                .uri("/evaluation")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(EvaluationRequest.builder().build()), EvaluationRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EvaluationResponse.class)
                .value(candidateResponse -> {
                            Assertions.assertThat(candidateResponse.getDni()).isEqualTo("7654321");
                            Assertions.assertThat(candidateResponse.getName()).isEqualTo("nombre test");
                            Assertions.assertThat(candidateResponse.getLastName()).isEqualTo("last name");
                        }
                );

    }
}
