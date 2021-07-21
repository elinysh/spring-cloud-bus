package com.linecorp.healthcare.survey.questionnaire.domain.question;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.linecorp.healthcare.survey.database.domain.AutoIncreasable;
import com.linecorp.healthcare.survey.questionnaire.domain.answer.Answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Document(collection = "questions")
@AllArgsConstructor
@NoArgsConstructor
public class Question implements AutoIncreasable {
    @Indexed
    private Long questionnaireId;
    @Indexed
    @Id
    private Long questionId;
    private QuestionType type;
    private String title;
    private String description;
    private boolean required;
    private AnswerType answerType;
    private int order;
    @Transient
    private List<Answer> answers;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public void setId(Long id) {
        this.questionId = id;
    }

    public enum QuestionType {
        SIMPLE,
        SELECTABLE,
        CHECKBOX
    }

    public enum AnswerType {
        SINGULAR,
        PLURAL
    }
}
