package com.linecorp.healthcare.survey.evaluation.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.survey.evaluation.domain.ResultTemplate;

public interface ResultTemplateRepository extends MongoRepository<ResultTemplate, Long> {
    Optional<ResultTemplate> findResultTemplateByQuestionnaireIdAndResultCode(Long questionnaireId, String resultCode);
}
