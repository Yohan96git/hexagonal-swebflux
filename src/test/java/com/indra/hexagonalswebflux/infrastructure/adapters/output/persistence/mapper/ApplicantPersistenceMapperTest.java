package com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.mapper;

import com.indra.hexagonalswebflux.domain.model.Applicant;
import com.indra.hexagonalswebflux.infrastructure.adapters.output.persistence.documents.ApplicantDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ApplicantPersistenceMapperTest {


    @Test
    public void ToApplicantDocumentTest(){
        //Given
        Applicant applicant = Applicant.builder()
                .dni("1234567")
                .name("name test")
                .lastName("lastName test")
                .phone("123124")
                .cvUrl("cv")
                .build();

        //When
        ApplicantDocument applicantDocument = ApplicantPersistenceMapper.ToApplicantDocument(applicant);

        //Then
        assertEquals(applicant.getDni(), applicantDocument.getDni());
        assertEquals(applicant.getName(), applicantDocument.getName());
        assertEquals(applicant.getLastName(), applicantDocument.getLastName());
        assertEquals(applicant.getPhone(), applicantDocument.getPhone());
        assertEquals(applicant.getCvUrl(), applicantDocument.getCvUrl());
    }

    @Test
    public void ToApplicantTest(){
        //Given
        ApplicantDocument document = ApplicantDocument.builder()
                .dni("1234568")
                .name("name test 1")
                .lastName("lastName test 1")
                .phone("123124")
                .cvUrl("cv.com")
                .build();

        //When
        Applicant applicant = ApplicantPersistenceMapper.ToApplicant(document);

        //Then
        assertEquals(applicant.getDni(), document.getDni());
        assertEquals(applicant.getName(), document.getName());
        assertEquals(applicant.getLastName(), document.getLastName());
        assertEquals(applicant.getPhone(), document.getPhone());
        assertEquals(applicant.getCvUrl(), document.getCvUrl());
    }
}
