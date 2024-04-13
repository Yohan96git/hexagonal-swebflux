package com.indra.hexagonalswebflux.infrastructure.adapters.input.rest.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CandidateUpdateRequest {

    @NotBlank(message="This value must not be empty or null")
    private String address;

    private String phone;

    @NotBlank(message="This value must not be empty or null")
    private String email;

    @NotBlank(message="This value must not be empty or null")
    private String cvUrl;

    @NotNull(message="This value must not be null")
    private Integer yearExp;

    @NotBlank(message="This value must not be empty or null")
    private String softwareKnowledge;

    @NotBlank(message="This value must not be empty or null")
    private String profileUrl;

}
