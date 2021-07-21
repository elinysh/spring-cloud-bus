package com.linecorp.healthcare.survey.evaluation.domain;

import java.util.Map;

import com.linecorp.healthcare.survey.condition.Condition;

import lombok.Data;

@Data
public class EvaluationTable {
    private String factorName;
    private Map<String, Condition> classMap;
}
