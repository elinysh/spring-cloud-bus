package com.linecorp.healthcare.triage.questionnaire.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.linecorp.healthcare.triage.questionnaire.domain.Questionnaire;
import com.linecorp.healthcare.triage.questionnaire.domain.answer.Answer;
import com.linecorp.healthcare.triage.questionnaire.domain.answer.descision.FactorModifier;
import com.linecorp.healthcare.triage.questionnaire.domain.question.Question;
import com.linecorp.healthcare.triage.questionnaire.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public List<FactorModifier> getFactorModifierByAnswerIds(List<Long> answerIds) {
        return getAnswers(answerIds)
                .stream()
                .flatMap(answer -> answer.getDecision().getFactorModifiers().stream())
                .collect(Collectors.toList());
    }

    private List<Answer> getAnswers(List<Long> answerIds) {
        return StreamSupport.stream(answerRepository.findAllById(answerIds).spliterator(), false)
                            .collect(Collectors.toList());
    }

    public void insertAnswers(Questionnaire questionnaire, List<Question> questions) {
        List<Answer> answers = questions.stream()
                                        .peek(question -> {
                                            question.getAnswers().forEach(answer -> {
                                                answer.setQuestionnaireId(questionnaire.getQuestionnaireId());
                                                answer.setQuestionId(question.getQuestionId());
                                            });
                                        })
                                        .flatMap(question -> question.getAnswers().stream())
                                        .collect(Collectors.toList());
        answerRepository.saveAll(answers);
    }
}
