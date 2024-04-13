package com.indra.hexagonalswebflux.application.ports.input;

import com.indra.hexagonalswebflux.domain.model.Evaluation;
import reactor.core.publisher.Mono;

public interface EvaluationServicePort {

    Mono<Evaluation> save(Evaluation evaluation);

}
