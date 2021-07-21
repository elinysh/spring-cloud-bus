package com.linecorp.healthcare.triage.questionnaire.domain.factor;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeName(ValueType.CODE_NAME)
@Data
@EqualsAndHashCode(callSuper = true)
public class CodeFactor extends Factor<String> {
    public CodeFactor() {
        super.setType(ValueType.CODE);
    }

    @Override
    public boolean isGreaterThan(String s) {
        throw new UnsupportedOperationException("code class doesn't use this method");
    }

    @Override
    public boolean isLessThan(String s) {
        throw new UnsupportedOperationException("code class doesn't use this method");
    }
}
