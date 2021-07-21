package com.linecorp.healthcare.triage.evaluation.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.evaluation.domain.Evaluator;

public interface EvaluatorRepository extends MongoRepository<Evaluator, Long> {
    Optional<Evaluator> findByQuestionnaireId(Long questionnaireId);
}
