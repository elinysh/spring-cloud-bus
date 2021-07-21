package com.linecorp.healthcare.survey.questionnaire.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.survey.questionnaire.domain.Questionnaire;

public interface QuestionnaireRepository extends MongoRepository<Questionnaire, Long> {
    Optional<Questionnaire> findQuestionnaireByQuestionnaireId(Long questionnaireId);
}
