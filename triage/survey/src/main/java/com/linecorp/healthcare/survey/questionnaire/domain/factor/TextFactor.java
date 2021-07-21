package com.linecorp.healthcare.survey.questionnaire.domain.factor;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonTypeName(ValueType.TEXT_NAME)
@Data
@EqualsAndHashCode(callSuper = true)
public class TextFactor extends Factor<String> {
    public TextFactor() {
        super.setType(ValueType.TEXT);
    }

    public TextFactor(String value) {
        super.setType(ValueType.TEXT);
        super.setValue(value);
    }

    @Override
    public boolean isGreaterThan(String s) {
        throw new UnsupportedOperationException("text class doesn't use this method");
    }

    @Override
    public boolean isLessThan(String s) {
        throw new UnsupportedOperationException("text class doesn't use this method");
    }
}
