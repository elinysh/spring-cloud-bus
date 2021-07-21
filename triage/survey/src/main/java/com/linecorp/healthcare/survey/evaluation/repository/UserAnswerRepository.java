package com.linecorp.healthcare.survey.evaluation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.survey.questionnaire.domain.answer.UserAnswer;

public interface UserAnswerRepository extends MongoRepository<UserAnswer, Long> {
}
