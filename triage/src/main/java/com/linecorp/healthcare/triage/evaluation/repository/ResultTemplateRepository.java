package com.linecorp.healthcare.triage.evaluation.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.evaluation.domain.ResultTemplate;

public interface ResultTemplateRepository extends MongoRepository<ResultTemplate, Long> {
    Optional<ResultTemplate> findResultTemplateByQuestionnaireIdAndResultCode(Long questionnaireId, String resultCode);
}
