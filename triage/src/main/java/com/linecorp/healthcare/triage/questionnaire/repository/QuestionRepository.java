package com.linecorp.healthcare.triage.questionnaire.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.questionnaire.domain.question.Question;

public interface QuestionRepository extends MongoRepository<Question, Long> {
    List<Question> findQuestionsByQuestionnaireIdIn(List<Long> questionnaireIds);
}
