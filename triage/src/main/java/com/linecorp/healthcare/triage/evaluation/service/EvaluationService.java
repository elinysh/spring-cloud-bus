package com.linecorp.healthcare.triage.evaluation.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linecorp.healthcare.exception.NotFoundException;
import com.linecorp.healthcare.triage.evaluation.domain.Evaluation;
import com.linecorp.healthcare.triage.evaluation.domain.Evaluator;
import com.linecorp.healthcare.triage.evaluation.domain.ResultTemplate;
import com.linecorp.healthcare.triage.evaluation.dto.EvaluationDto;
import com.linecorp.healthcare.triage.evaluation.repository.EvaluationRepository;
import com.linecorp.healthcare.triage.evaluation.repository.EvaluatorRepository;
import com.linecorp.healthcare.triage.evaluation.repository.UserAnswerRepository;
import com.linecorp.healthcare.triage.questionnaire.domain.answer.descision.FactorModifier;
import com.linecorp.healthcare.triage.questionnaire.domain.factor.Factor;
import com.linecorp.healthcare.triage.questionnaire.service.AnswerService;
import com.linecorp.healthcare.triage.questionnaire.service.QuestionnaireService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EvaluationService {


    private final EvaluationRepository evaluationRepository;
    private final EvaluatorRepository evaluatorRepository;
    private final UserAnswerRepository userAnswerRepository;

    private final AnswerService answerService;
    private final QuestionnaireService questionnaireService;
    private final ResultTemplateService resultTemplateService;

    public Evaluator save(Evaluator evaluator) {
        return evaluatorRepository.save(evaluator);
    }

    @Transactional
    public EvaluationDto evaluate(CreateEvaluation createEvaluation) {
        userAnswerRepository.saveAll(createEvaluation.convertUserAnswer());

        Questionnaire questionnaire = questionnaireService.getOnlyQuestionnaire(createEvaluation.getQuestionnaireId());
        Evaluator evaluator = getEvaluator(createEvaluation.getQuestionnaireId());

        List<FactorModifier> factorModifiers = answerService.getFactorModifierByAnswerIds(createEvaluation.convertAnswerIds());
        Map<String, Factor> calculatedFactorMap = questionnaire.calculateFactors(factorModifiers);
        String resultCandidateCode = evaluator.calculateResultCandidateCode(calculatedFactorMap);
        String resultCode = evaluator.findFinalResult(resultCandidateCode);

        ResultTemplate resultTemplate = resultTemplateService.getResultTemplateByResultCode(questionnaire.getQuestionnaireId(), resultCode);
        Evaluation evaluation = Evaluation.builder()
                                          .questionnaireId(questionnaire.getQuestionnaireId())
                                          .applicationId(questionnaire.getApplicationId())
                                          .factors(calculatedFactorMap)
                                          .resultTemplateId(resultTemplate.getId())
                                          .resultCode(resultCode)
                                          .userId(createEvaluation.getUserId())
                                          .build();

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        EvaluationDto evaluationDto = ObjectMapperConverter.convert(savedEvaluation, EvaluationDto.class);

        return evaluationDto.transfer(resultTemplate);
    }

    private Evaluator getEvaluator(Long questionnaireId) {
        return evaluatorRepository.findByQuestionnaireId(questionnaireId)
                                  .orElseThrow(() -> new NotFoundException("id: " + questionnaireId));
    }
}
