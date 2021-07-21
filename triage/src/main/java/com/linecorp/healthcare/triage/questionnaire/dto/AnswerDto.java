package com.linecorp.healthcare.triage.questionnaire.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.descision.Decision;

import lombok.Data;

@Data
public class AnswerDto {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long questionId;
    private Long answerId;
    private String displayText;
    private Decision decision;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int order;
}
