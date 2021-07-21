package com.linecorp.healthcare.survey.evaluation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.survey.evaluation.domain.Evaluation;

public interface EvaluationRepository extends MongoRepository<Evaluation, Long> {}
