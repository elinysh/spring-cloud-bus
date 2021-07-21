package com.linecorp.healthcare.triage.questionnaire.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.questionnaire.domain.answer.Answer;

public interface AnswerRepository extends MongoRepository<Answer, Long> {
    List<Answer> findAnswersByQuestionnaireIdIn(List<Long> questionnaireIds);
}
