package com.linecorp.healthcare.triage.questionnaire.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.questionnaire.domain.Questionnaire;

public interface QuestionnaireRepository extends MongoRepository<Questionnaire, Long> {
    Optional<Questionnaire> findQuestionnaireByQuestionnaireId(Long questionnaireId);
}
