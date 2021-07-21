package com.linecorp.healthcare.survey.questionnaire.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Sort;

import com.linecorp.healthcare.survey.questionnaire.domain.Questionnaire.Status;
import com.linecorp.healthcare.survey.questionnaire.domain.factor.Factor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionnaireDto {
    private Long questionnaireId;
    private String title;
    private String description;
    private Status status;
    private Long version;
    private List<QuestionDto> questions;
    private Map<String, Factor> factors;
    private String applicationId;
    private LocalDateTime publishedAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private Map<String, Object> data;

    public QuestionnaireDto transfer(List<QuestionDto> questionDtos) {
        this.setQuestions(questionDtos);
        return this;
    }

    public static Sort sortByPublishedAt() {
        return Sort.by(Sort.Direction.DESC, "publishedAt");
    }
}
