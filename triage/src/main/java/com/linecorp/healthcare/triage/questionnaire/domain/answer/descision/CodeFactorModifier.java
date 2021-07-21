package com.linecorp.healthcare.triage.questionnaire.domain.answer.descision;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName(ValueType.CODE_NAME)
public class CodeFactorModifier extends FactorModifier<String> {
    public CodeFactorModifier() {
        super.setType(ValueType.CODE);
    }

    @Override
    public String add(String aDouble) {
        throw new UnsupportedOperationException("String type, add is not unsupported.");
    }

    @Override
    public String subtraction(String aDouble) {
        throw new UnsupportedOperationException("String type, subtraction is not unsupported.");
    }
}
