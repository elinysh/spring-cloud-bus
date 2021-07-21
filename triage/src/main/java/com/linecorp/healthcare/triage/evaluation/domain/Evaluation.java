package com.linecorp.healthcare.triage.evaluation.domain;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.linecorp.healthcare.triage.database.domain.AutoIncreasable;
import com.linecorp.healthcare.triage.questionnaire.domain.factor.Factor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "evaluations")
public class Evaluation implements AutoIncreasable {
    @Id
    private Long evaluationId;
    @Indexed
    private Long questionnaireId;
    private String applicationId;
    private Long resultTemplateId;
    @Version
    private Long version;
    @Indexed
    private Long userId;

    private Map<String, Factor> factors;
    private String resultCode;

    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public void setId(Long id) {
        this.evaluationId = id;
    }
}
