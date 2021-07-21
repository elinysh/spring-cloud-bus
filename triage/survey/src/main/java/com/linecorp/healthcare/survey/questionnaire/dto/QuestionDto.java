package com.linecorp.healthcare.survey.questionnaire.dto;

import java.util.List;

import com.linecorp.healthcare.survey.questionnaire.domain.question.Question.AnswerType;
import com.linecorp.healthcare.survey.questionnaire.domain.question.Question.QuestionType;

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
