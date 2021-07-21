package com.linecorp.healthcare.survey.questionnaire.domain.answer;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.linecorp.healthcare.survey.database.domain.AutoIncreasable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userAnswers")
public class UserAnswer implements AutoIncreasable {
    @Transient
    public static final String SEQUENCE_NAME = "user_answer_sequence";

    @Id
    private Long id;
    private Long questionnaireId;
    @Indexed
    private Long questionId;
    private Long answerId;
    @Indexed
    private Long userId;

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
