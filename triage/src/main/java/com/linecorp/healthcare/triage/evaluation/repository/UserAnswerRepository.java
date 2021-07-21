package com.linecorp.healthcare.triage.evaluation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.questionnaire.domain.answer.UserAnswer;

public interface UserAnswerRepository extends MongoRepository<UserAnswer, Long> {
}
