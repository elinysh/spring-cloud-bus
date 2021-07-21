package com.linecorp.healthcare.survey.questionnaire.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.linecorp.healthcare.survey.questionnaire.domain.Questionnaire;
import com.linecorp.healthcare.survey.questionnaire.domain.question.Question;
import com.linecorp.healthcare.survey.questionnaire.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> insertQuestions(Questionnaire questionnaire) {
        List<Question> questions = questionnaire.getQuestions()
                                                .stream()
                                                .peek(question -> {
                                                    question.setQuestionnaireId(questionnaire.getQuestionnaireId());
                                                })
                                                .collect(Collectors.toList());

        List<Question> createdQuestions = questionRepository.saveAll(questions);
        return createdQuestions;
    }
}
