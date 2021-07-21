package com.linecorp.healthcare.triage.questionnaire.domain.factor;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.triage.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeName(ValueType.DOUBLE_NAME)
@Data
@EqualsAndHashCode(callSuper = true)
public class DoubleFactor extends Factor<Double> {
    public DoubleFactor() {
        super.setType(ValueType.DOUBLE);
    }

    @Override
    public boolean isGreaterThan(Double comp) {
        Double value = getValue();
        return value != null && value > comp;
    }

    @Override
    public boolean isLessThan(Double comp) {
        Double value = getValue();
        return value != null && value < comp;
    }
}
