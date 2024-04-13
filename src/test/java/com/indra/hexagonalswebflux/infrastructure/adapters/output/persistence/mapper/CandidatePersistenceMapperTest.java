package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.mapper;

import com.indra.hexagonalswebflux.domain.model.Candidate;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.CandidateDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class CandidatePersistenceMapperTest {

    @Test
    public void ToApplicantDocumentTest(){
        //Given
        Candidate candidate = Candidate.builder()
                .dni("1234567")
                .name("name test")
                .lastName("lastName test")
                .phone("123124")
                .cvUrl("cv")
                .build();

        //When
        CandidateDocument candidateDocument = CandidatePersistenceMapper.ToCandidateDocument(candidate);

        //Then
        assertEquals(candidate.getDni(), candidateDocument.getDni());
        assertEquals(candidate.getName(), candidateDocument.getName());
        assertEquals(candidate.getLastName(), candidateDocument.getLastName());
        assertEquals(candidate.getPhone(), candidateDocument.getPhone());
        assertEquals(candidate.getCvUrl(), candidateDocument.getCvUrl());
    }
}
