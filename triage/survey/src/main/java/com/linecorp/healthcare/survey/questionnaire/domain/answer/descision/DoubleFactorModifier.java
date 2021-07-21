package com.linecorp.healthcare.survey.questionnaire.domain.answer.descision;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName(ValueType.DOUBLE_NAME)
public class DoubleFactorModifier extends FactorModifier<Double> {
    public DoubleFactorModifier() {
        super.setType(ValueType.DOUBLE);
    }

    @Override
    public Double add(Double aDouble) {
        Double value = getValue();
        return value + aDouble;
    }

    @Override
    public Double subtraction(Double aDouble) {
        Double value = getValue();
        return aDouble - value;
    }
}
