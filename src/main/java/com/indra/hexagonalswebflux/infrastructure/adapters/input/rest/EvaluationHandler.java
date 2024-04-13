package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import com.indra.hexagonalswebflux.application.ports.input.EvaluationServicePort;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.mapper.EvaluationMapper;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request.EvaluationRequest;
import com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.response.EvaluationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EvaluationHandler {

    private final EvaluationServicePort servicePort;

    public Mono<ServerResponse> saveEvaluation(ServerRequest request) {
        return request.bodyToMono(EvaluationRequest.class).flatMap(evaluationRequest -> ServerResponse.ok().body( servicePort.save(EvaluationMapper.CreateRequestToEvaluation(evaluationRequest)).map(EvaluationMapper::EvaluationToResponse), EvaluationResponse.class));
    }
}
