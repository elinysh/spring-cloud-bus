package com.linecorp.healthcare.triage.questionnaire.dto;

import java.util.List;

import com.linecorp.healthcare.triage.questionnaire.domain.question.Question.AnswerType;
import com.linecorp.healthcare.triage.questionnaire.domain.question.Question.QuestionType;

import lombok.Data;

@Data
public class QuestionDto {
    private Long questionId;
    private QuestionType type;
    private String title;
    private String description;

    private boolean required;
    private AnswerType answerType;
    private List<AnswerDto> answers;
}
