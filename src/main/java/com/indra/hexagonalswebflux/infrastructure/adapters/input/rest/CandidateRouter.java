package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class CandidateRouter {

    @Bean(name = "routes-candidate")
    public RouterFunction<ServerResponse> routes(CandidateHandler handler) {
        return RouterFunctions.
                route(GET("/candidates").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(GET("/candidates/{dni}").and(accept(MediaType.APPLICATION_JSON)), handler::findByDni)
                .andRoute(POST("/candidate").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(PUT("/candidates/{dni}").and(accept(MediaType.APPLICATION_JSON)), handler::update)
                .andRoute(DELETE("/candidates/{dni}").and(accept(MediaType.APPLICATION_JSON)), handler::DeleteByDni);
    }

}
