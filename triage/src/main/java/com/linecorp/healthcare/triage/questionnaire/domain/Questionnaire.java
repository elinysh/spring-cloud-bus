package com.linecorp.healthcare.triage.questionnaire.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.linecorp.healthcare.exception.NotFoundException;
import com.linecorp.healthcare.triage.database.domain.AutoIncreasable;
import com.linecorp.healthcare.triage.questionnaire.domain.answer.descision.FactorModifier;
import com.linecorp.healthcare.triage.questionnaire.domain.factor.Factor;
import com.linecorp.healthcare.triage.questionnaire.domain.question.Question;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Document(collection = "questionnaires")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Questionnaire implements AutoIncreasable {
    @Indexed
    @Id
    private Long questionnaireId;
    @Indexed
    private String applicationId;
    private String title;
    private String description;
    private Status status;
    private Long version;
    @Transient
    private List<Question> questions;
    private Map<String, Factor> factorMap;
    private Map<String, Object> data;

    @Indexed
    private LocalDateTime publishedAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public void setId(Long id) {
        this.questionnaireId = id;
    }

    public Map<String, Factor> calculateFactors(List<FactorModifier> factorModifiers) {
        if (factorMap == null) {
            throw new NotFoundException("factor map is not founded.");
        }

        factorModifiers.forEach(factorModifier -> {
            Factor factor = factorMap.get(factorModifier.getCode());
            factor.setValue(factorModifier.calculate(factor.getValue()));
        });

        return factorMap;
    }

    public enum Status {
        PUBLISHED, NOT_PUBLISHED, EXPIRED
    }

}
