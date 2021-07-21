package com.linecorp.healthcare.triage.evaluation.dto;

import java.time.LocalDateTime;

import com.linecorp.healthcare.triage.evaluation.domain.ResultTemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EvaluationDto {
    private Long evaluationId;
    private Long questionnaireId;
    private String applicationId;
    private Long userId;
    private ResultTemplate resultTemplate;
    private LocalDateTime createdAt;

    public EvaluationDto transfer(ResultTemplate resultTemplate) {
        this.setResultTemplate(resultTemplate);
        return this;
    }
}
