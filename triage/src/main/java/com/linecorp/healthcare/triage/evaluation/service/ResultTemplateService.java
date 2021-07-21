package com.linecorp.healthcare.triage.evaluation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.linecorp.healthcare.disease.microservice.survey.evaluation.domain.ResultTemplate;
import com.linecorp.healthcare.disease.microservice.survey.evaluation.repository.ResultTemplateRepository;
import com.linecorp.healthcare.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ResultTemplateService {
    private final ResultTemplateRepository resultTemplateRepository;

    public ResultTemplate getResultTemplateByResultCode(Long questionnaireId, String resultCode) {
        if (!StringUtils.hasText(resultCode)) {
            throw new IllegalArgumentException("resultCode: " + resultCode);
        }

        return resultTemplateRepository.findResultTemplateByQuestionnaireIdAndResultCode(questionnaireId, resultCode)
                                       .orElseThrow(() -> new NotFoundException("resultTemplate code : " + resultCode));
    }
}
