package com.linecorp.healthcare.triage.evaluation.domain;

import java.util.Map;

import com.linecorp.healthcare.triage.condition.Condition;

import lombok.Data;

@Data
public class EvaluationTable {
    private String factorName;
    private Map<String, Condition> classMap;
}
