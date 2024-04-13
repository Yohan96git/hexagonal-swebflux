package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class ApplicantRouter {



    @Bean(name = "routes-applicant")
    public RouterFunction<ServerResponse> routes(ApplicantHandler handler) {
        return RouterFunctions.
                route(GET("/applicants").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(GET("/applicants/{dni}").and(accept(MediaType.APPLICATION_JSON)), handler::findByDni)
                .andRoute(POST("/applicants").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(PUT("/applicants/{dni}").and(accept(MediaType.APPLICATION_JSON)), handler::update)
                .andRoute(DELETE("/applicants/{dni}").and(accept(MediaType.APPLICATION_JSON)), handler::DeleteByDni);
    }

}
