package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class EvaluationRouter {

    @Bean(name = "routes-evaluation")
    public RouterFunction<ServerResponse> routes(EvaluationHandler handler) {
        return RouterFunctions.
                route(POST("/evaluation").and(accept(MediaType.APPLICATION_JSON)), handler::saveEvaluation);
    }

}
