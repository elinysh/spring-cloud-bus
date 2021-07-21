package com.linecorp.healthcare.survey.questionnaire.domain.answer;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.linecorp.healthcare.survey.database.domain.AutoIncreasable;
import com.linecorp.healthcare.survey.questionnaire.domain.answer.descision.Decision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "answers")
public class Answer implements AutoIncreasable {
    @Indexed
    private Long questionnaireId;
    @Indexed
    private Long questionId;
    @Id
    private Long answerId;
    private String displayText;
    private int order;
    private Decision decision;
    private ValueType type;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public void setId(Long id) {
        this.answerId = id;
    }
}
