package com.linecorp.healthcare.survey.evaluation.domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.linecorp.healthcare.exception.NotFoundException;
import com.linecorp.healthcare.exception.UnknownException;
import com.linecorp.healthcare.survey.condition.TextCondition;
import com.linecorp.healthcare.survey.database.domain.AutoIncreasable;
import com.linecorp.healthcare.survey.questionnaire.domain.factor.Factor;
import com.linecorp.healthcare.survey.questionnaire.domain.factor.TextFactor;

import lombok.Data;

@Data
@Document(collection = "evaluators")
public class Evaluator implements AutoIncreasable {
    List<EvaluationTable> evaluationTables;
    Map<String, TextCondition> resultMap;
    @Id
    private Long evaluatorId;
    private Long questionnaireId;

    @Override
    public void setId(Long id) {
        this.evaluatorId = id;
    }

    public String calculateResultCandidateCode(Map<String, Factor> calculatedFactorMap) {
        return calculatedFactorMap.values()
                                  .stream()
                                  .map(factor -> evaluationTables
                                          .stream()
                                          .filter(evaluationTable -> evaluationTable.getFactorName().equals(factor.getName()))
                                          .findAny()
                                          .map(evaluationTableForFactor -> findResultCandidateCode(factor, evaluationTableForFactor))
                                          .orElseThrow(NotFoundException::new))
                                  .reduce((s1, s2) -> s1 + s2)
                                  .stream()
                                  .findAny()
                                  .orElseThrow(() -> new UnknownException("calcalating error is occured."));
    }

    private String findResultCandidateCode(Factor factor, EvaluationTable evaluationTableForFactor) {
        return evaluationTableForFactor.getClassMap()
                                       .entrySet()
                                       .stream()
                                       .filter(entry -> entry.getValue().matches(factor))
                                       .map(Entry::getKey)
                                       .findAny()
                                       .orElseThrow(() -> new NotFoundException("Evaluation table is not exist"));
    }

    public String findFinalResult(String resultCandidateCode) {
        return resultMap
                .entrySet()
                .stream()
                .filter(resultMap -> {
                    TextCondition textCondition = resultMap.getValue();
                    return textCondition.matches(new TextFactor(resultCandidateCode));
                })
                .map(Entry::getKey)
                .findAny()
                .orElse(null);
    }
}
