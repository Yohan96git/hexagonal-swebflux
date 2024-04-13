package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import com.indra.hexagonalswebflux.application.ports.input.CandidateServicePort;
import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.mapper.CandidateMapper;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.CandidateCreateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.CandidateUpdateRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.CandidateCreateResponse;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.CandidateUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CandidateHandler {

    private final CandidateServicePort servicePort;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<Candidate> candidateFlux = servicePort.findAll();
        Flux<CandidateCreateResponse> candidateResponseFlux = candidateFlux.map(CandidateMapper::CandidateToCreateResponse);
        return ServerResponse.ok().body(candidateResponseFlux, CandidateCreateResponse.class);
    }

    public Mono<ServerResponse> findByDni(ServerRequest request) {
        Mono<Candidate> candidateFlux = servicePort.findByDni(request.pathVariable("dni"));
        Mono<CandidateCreateResponse> candidateResponseFlux = candidateFlux.map(CandidateMapper::CandidateToCreateResponse);
        return ServerResponse.ok().body(candidateResponseFlux, CandidateCreateResponse.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(CandidateCreateRequest.class).flatMap(candidateRequest -> ServerResponse.ok().body( servicePort.save(CandidateMapper.CreateRequestToCandidate(candidateRequest)).map(CandidateMapper::CandidateToCreateResponse), CandidateCreateResponse.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(CandidateUpdateRequest.class).flatMap(candidateRequest -> ServerResponse.ok().body( servicePort.update(request.pathVariable("dni"), CandidateMapper.UpdateRequestToCandidate(candidateRequest)).map(CandidateMapper::CandidateToUpdateResponse), CandidateUpdateResponse.class));
    }

    public Mono<ServerResponse> DeleteByDni(ServerRequest request) {
        servicePort.deleteByDni(request.pathVariable("dni")).subscribe();
        return ServerResponse.ok().build();
    }

}
