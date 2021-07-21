package com.linecorp.healthcare.survey.questionnaire.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.survey.questionnaire.domain.question.Question;

public interface QuestionRepository extends MongoRepository<Question, Long> {
    List<Question> findQuestionsByQuestionnaireIdIn(List<Long> questionnaireIds);
}
