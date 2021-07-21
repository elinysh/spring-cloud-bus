package com.linecorp.healthcare.survey.evaluation.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.linecorp.healthcare.exception.NotFoundException;
import com.linecorp.healthcare.survey.evaluation.domain.ResultTemplate;
import com.linecorp.healthcare.survey.evaluation.repository.ResultTemplateRepository;

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
