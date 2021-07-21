package com.linecorp.healthcare.survey.condition;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonTypeName(ValueType.DOUBLE_NAME)
@EqualsAndHashCode(callSuper = true)
public class DoubleCondition extends Condition<Double> {
    public DoubleCondition() {
        super.setType(ValueType.DOUBLE);
    }
}
