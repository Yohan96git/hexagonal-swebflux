package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import com.indra.hexagonalswebflux.application.ports.input.ApplicantServicePort;
import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.ApplicantCreateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.ApplicantUpdateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantCreateResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantUpdateResponse;
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
@ContextConfiguration(classes = {ApplicantRouter.class, ApplicantHandler.class})
@WebFluxTest
public class ApplicantHandlerTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private ApplicantServicePort applicantServicePort;

    private WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void getApplicantByDniTest() {

        Mono<Applicant> applicantMono = Mono.just(Applicant
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build());
        when(applicantServicePort.findByDni("7654321")).thenReturn(applicantMono);
        webTestClient.get()
                .uri("/candidates/7654321")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApplicantResponse.class)
                .value(applicantResponse -> {
                            Assertions.assertThat(applicantResponse.getName()).isEqualTo("nombre test");
                            Assertions.assertThat(applicantResponse.getLastName()).isEqualTo("last name");
                            Assertions.assertThat(applicantResponse.getDni()).isEqualTo("7654321");
                            Assertions.assertThat(applicantResponse.getEmail()).isEqualTo("email@com");
                            Assertions.assertThat(applicantResponse.getPhone()).isEqualTo("987654321");
                        }
                );
    }


    @Test
    public void findAllTest() {

        Applicant applicant1 = Applicant.builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build();
        Applicant applicant2 = Applicant.builder()
                .name("nombre test 2")
                .lastName("last name 2")
                .dni("7654321 2")
                .email("email@com 2")
                .phone("9876543210")
                .cvUrl("cv.com")
                .build();

        when(applicantServicePort.findAll()).thenReturn(Flux.just(applicant1, applicant2));

        webTestClient.get()
                .uri("/applicants")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ApplicantResponse.class)
                .value(applicantResponse -> {
                            Assertions.assertThat(applicantResponse.get(0).getName()).isEqualTo("nombre test");
                            Assertions.assertThat(applicantResponse.get(0).getLastName()).isEqualTo("last name");
                            Assertions.assertThat(applicantResponse.get(0).getDni()).isEqualTo("7654321");
                            Assertions.assertThat(applicantResponse.get(0).getEmail()).isEqualTo("email@com");
                            Assertions.assertThat(applicantResponse.get(0).getPhone()).isEqualTo("987654321");

                            Assertions.assertThat(applicantResponse.get(1).getName()).isEqualTo("nombre test 2");
                            Assertions.assertThat(applicantResponse.get(1).getLastName()).isEqualTo("last name 2");
                            Assertions.assertThat(applicantResponse.get(1).getDni()).isEqualTo("7654321 2");
                            Assertions.assertThat(applicantResponse.get(1).getEmail()).isEqualTo("email@com 2");
                            Assertions.assertThat(applicantResponse.get(1).getPhone()).isEqualTo("9876543210");

                        }
                );
    }


    @Test
    public void saveTest() {

        Mono<Applicant> applicantMono = Mono.just(Applicant
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build());

        when(applicantServicePort.save(any())).thenReturn(applicantMono);

        webTestClient.post()
                .uri("/applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(ApplicantCreateRequest.builder().build()), ApplicantCreateRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApplicantCreateResponse.class)
                .value(applicantResponse -> {
                            Assertions.assertThat(applicantResponse.getName()).isEqualTo("nombre test");
                            Assertions.assertThat(applicantResponse.getLastName()).isEqualTo("last name");
                            Assertions.assertThat(applicantResponse.getDni()).isEqualTo("7654321");
                        }
                );

    }

    @Test
    public void updateTest() {

        Mono<Applicant> applicantMono = Mono.just(Applicant
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build());

        when(applicantServicePort.update(any(), any())).thenReturn(applicantMono);

        webTestClient.put()
                .uri("/applicants/7654321")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(ApplicantUpdateRequest.builder().build()), ApplicantUpdateRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApplicantUpdateResponse.class)
                .value(response -> {
                            Assertions.assertThat(response.getPhone()).isEqualTo("987654321");
                            Assertions.assertThat(response.getEmail()).isEqualTo("email@com");
                        }
                );

    }
}
