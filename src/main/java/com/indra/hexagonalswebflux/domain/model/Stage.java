package com.indra.hexagonalswebflux.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Stage {

    private String id;
    private String code;
    private String name;
    private String score;
    private String status;
    private String evaluatorName;
    private String evaluatorObservation;
}
