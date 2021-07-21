package com.linecorp.healthcare.triage.questionnaire.domain.answer.descision;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName(ValueType.TEXT_NAME)
public class TextFactorModifier extends FactorModifier<String> {
    public TextFactorModifier() {
        super.setType(ValueType.TEXT);
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
