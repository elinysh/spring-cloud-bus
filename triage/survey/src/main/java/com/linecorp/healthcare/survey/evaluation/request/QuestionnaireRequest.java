package com.linecorp.healthcare.survey.evaluation.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.linecorp.healthcare.survey.questionnaire.domain.Questionnaire.Status;
import com.linecorp.healthcare.survey.questionnaire.domain.factor.Factor;
import com.linecorp.healthcare.survey.questionnaire.domain.question.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class QuestionnaireRequest {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateQuestionnaire {
        private String title;
        private String description;
        private Status status;
        private Long version;
        private List<Question> questions;
        private Map<String, Factor> factorMap;
        private String applicationId;
        private LocalDateTime publishedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Map<String, Object> data;
    }
}
