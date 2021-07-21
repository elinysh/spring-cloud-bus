package com.linecorp.healthcare.triage.evaluation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.evaluation.domain.Evaluation;

public interface EvaluationRepository extends MongoRepository<Evaluation, Long> {}
