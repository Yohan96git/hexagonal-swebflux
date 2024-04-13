package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import com.indra.hexagonalswebflux.application.ports.input.ApplicantServicePort;
import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.mapper.ApplicantMapper;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.ApplicantCreateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.ApplicantUpdateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.EvaluationRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantCreateResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.ApplicantUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ApplicantHandler {

    private final ApplicantServicePort servicePort;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Applicant> applicantFlux = servicePort.findAll();
        Flux<ApplicantResponse> applicantResponseFlux = applicantFlux.map(ApplicantMapper::ApplicantToResponse);
        return ServerResponse.ok().body(applicantResponseFlux, ApplicantCreateResponse.class);
    }

    public Mono<ServerResponse> findByDni(ServerRequest request) {
        Mono<Applicant> applicantFlux = servicePort.findByDni(request.pathVariable("dni"));
        Mono<ApplicantResponse> applicantResponseFlux = applicantFlux.map(ApplicantMapper::ApplicantToResponse);
        return ServerResponse.ok().body(applicantResponseFlux, ApplicantCreateResponse.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(ApplicantCreateRequest.class).flatMap(applicantRequest -> ServerResponse.ok().body( servicePort.save(ApplicantMapper.CreateRequestToApplicant(applicantRequest)).map(ApplicantMapper::ApplicantToCreateResponse), ApplicantCreateResponse.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(ApplicantUpdateRequest.class).flatMap(applicantRequest -> ServerResponse.ok().body( servicePort.update(request.pathVariable("dni"), ApplicantMapper.UpdateRequestToApplicant(applicantRequest)).map(ApplicantMapper::ApplicantToUpdateResponse), ApplicantUpdateResponse.class));
    }

    public Mono<ServerResponse> DeleteByDni(ServerRequest request) {
        servicePort.deleteByDni(request.pathVariable("dni")).subscribe();
        return ServerResponse.ok().build();
    }

}
