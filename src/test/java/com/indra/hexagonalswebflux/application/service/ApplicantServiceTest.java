package com.indra.hexagonalswebflux.application.service;

import com.indra.hexagonalswebflux.application.ports.output.ApplicantPersistencePort;
import com.indra.hexagonalswebflux.domain.model.Applicant;
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
public class ApplicantServiceTest {

    @InjectMocks
    private ApplicantService applicantService;

    @Mock
    private ApplicantPersistencePort applicantPersistencePort;

    @Test
    public void findByDniTest() {
        Applicant applicant = Applicant
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build();

        when(applicantPersistencePort.findByDni(applicant.getDni())).thenReturn(Mono.just(applicant));
        Mono<Applicant> applicantMono = applicantService.findByDni(applicant.getDni());
        StepVerifier
                .create(applicantMono)
                .consumeNextWith(applicantResponse -> {
                    Assertions.assertThat(applicantResponse.getName()).isEqualTo("nombre test");
                    Assertions.assertThat(applicantResponse.getLastName()).isEqualTo("last name");
                    Assertions.assertThat(applicantResponse.getDni()).isEqualTo("7654321");
                    Assertions.assertThat(applicantResponse.getEmail()).isEqualTo("email@com");
                    Assertions.assertThat(applicantResponse.getPhone()).isEqualTo("987654321");                })
                .verifyComplete();
    }

    @Test
    public void findAll() {
        Applicant applicant1 = Applicant
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build();

        Applicant applicant2 = Applicant
                .builder()
                .name("nombre test 2")
                .lastName("last name 2")
                .dni("7654321 2")
                .email("email@com 2")
                .phone("9876543210")
                .cvUrl("cv.com 2")
                .build();

        when(applicantPersistencePort.findAll()).thenReturn(Flux.just(applicant1, applicant2));
        Flux<Applicant> applicantFlux = applicantService.findAll();
        StepVerifier
                .create(applicantFlux)
                .assertNext(response -> {
                    Assertions.assertThat(response.getName()).isEqualTo("nombre test");
                    Assertions.assertThat(response.getLastName()).isEqualTo("last name");
                    Assertions.assertThat(response.getDni()).isEqualTo("7654321");
                    Assertions.assertThat(response.getEmail()).isEqualTo("email@com");
                    Assertions.assertThat(response.getPhone()).isEqualTo("987654321");
                })
                .assertNext(response -> {
                    Assertions.assertThat(response.getName()).isEqualTo("nombre test 2");
                    Assertions.assertThat(response.getLastName()).isEqualTo("last name 2");
                    Assertions.assertThat(response.getDni()).isEqualTo("7654321 2");
                    Assertions.assertThat(response.getEmail()).isEqualTo("email@com 2");
                    Assertions.assertThat(response.getPhone()).isEqualTo("9876543210");
                })
                .verifyComplete();
    }

    @Test
    public void saveTest() {
        Applicant applicant = Applicant
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build();

        when(applicantPersistencePort.findByDni(applicant.getDni())).thenReturn(Mono.empty());
        when(applicantPersistencePort.save(applicant)).thenReturn(Mono.just(applicant));

        Mono<Applicant> applicantMono = applicantService.save(applicant);
        StepVerifier
                .create(applicantMono)
                .consumeNextWith(applicantResponse -> {
                    Assertions.assertThat(applicantResponse.getName()).isEqualTo("nombre test");
                    Assertions.assertThat(applicantResponse.getLastName()).isEqualTo("last name");
                    Assertions.assertThat(applicantResponse.getDni()).isEqualTo("7654321");
                    Assertions.assertThat(applicantResponse.getEmail()).isEqualTo("email@com");
                    Assertions.assertThat(applicantResponse.getPhone()).isEqualTo("987654321");                })
                .verifyComplete();
    }

    @Test
    public void updateTest() {
        Applicant applicant = Applicant
                .builder()
                .name("nombre test")
                .lastName("last name")
                .dni("7654321")
                .email("email@com")
                .phone("987654321")
                .cvUrl("cv.com")
                .build();

        when(applicantPersistencePort.findByDni(applicant.getDni())).thenReturn(Mono.just(applicant));
        when(applicantPersistencePort.save(applicant)).thenReturn(Mono.just(applicant));

        Mono<Applicant> applicantMono = applicantService.save(applicant);
        StepVerifier
                .create(applicantMono)
                .consumeNextWith(applicantResponse -> {
                    Assertions.assertThat(applicantResponse.getName()).isEqualTo("nombre test");
                    Assertions.assertThat(applicantResponse.getLastName()).isEqualTo("last name");
                    Assertions.assertThat(applicantResponse.getDni()).isEqualTo("7654321");
                    Assertions.assertThat(applicantResponse.getEmail()).isEqualTo("email@com");
                    Assertions.assertThat(applicantResponse.getPhone()).isEqualTo("987654321");                })
                .verifyComplete();
    }
}
