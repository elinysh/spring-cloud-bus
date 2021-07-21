package com.linecorp.healthcare.survey.evaluation.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.survey.evaluation.domain.Evaluator;

public interface EvaluatorRepository extends MongoRepository<Evaluator, Long> {
    Optional<Evaluator> findByQuestionnaireId(Long questionnaireId);
}
