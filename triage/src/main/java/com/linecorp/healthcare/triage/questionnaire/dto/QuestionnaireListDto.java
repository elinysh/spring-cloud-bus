package com.linecorp.healthcare.triage.questionnaire.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class QuestionnaireListDto {
    List<QuestionnaireDto> questionnaireDtos;
}
