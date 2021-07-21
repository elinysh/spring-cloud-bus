package com.linecorp.healthcare.survey.questionnaire.domain.answer.descision;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {
    private Type type;
    private Long nextQuestionId;

    public enum Type {
        TERMINATION,
        TRANSITION;
    }
}
