package com.linecorp.healthcare.triage.evaluation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.healthcare.triage.evaluation.domain.Evaluator;
import com.linecorp.healthcare.triage.evaluation.domain.ResultTemplate;
import com.linecorp.healthcare.triage.evaluation.dto.EvaluationDto;
import com.linecorp.healthcare.triage.evaluation.repository.ResultTemplateRepository;
import com.linecorp.healthcare.triage.evaluation.request.EvaluationRequest;
import com.linecorp.healthcare.triage.evaluation.service.EvaluationService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final ResultTemplateRepository resultTemplateRepository;

    @ApiOperation("save evaluation")
    @PostMapping("/evaluations")
    public EvaluationDto saveEvaluation(@RequestBody EvaluationRequest.CreateEvaluation createEvaluation) {
        return evaluationService.evaluate(createEvaluation);
    }

    @ApiOperation("save a evaluator")
    @PostMapping("/evaluators")
    public Evaluator saveEvaluator(@RequestBody Evaluator evaluator) {
        return evaluationService.save(evaluator);
    }

    @ApiOperation("save a result template")
    @PostMapping("/result-templates")
    public List<ResultTemplate> saveEvaluator(@RequestBody List<ResultTemplate> resultTemplates) {
        return resultTemplateRepository.saveAll(resultTemplates);
    }
}
