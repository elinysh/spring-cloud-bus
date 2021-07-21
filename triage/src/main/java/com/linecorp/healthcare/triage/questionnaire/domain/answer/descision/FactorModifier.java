package com.linecorp.healthcare.triage.questionnaire.domain.answer.descision;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;

@Data
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
                      @Type(value = DoubleFactorModifier.class, name = ValueType.DOUBLE_NAME),
                      @Type(value = TextFactorModifier.class, name = ValueType.TEXT_NAME),
                      @Type(value = CodeFactorModifier.class, name = ValueType.CODE_NAME)
              })
public abstract class FactorModifier<T> implements Operator<T> {
    private String code;
    private OperatorType operatorType;
    private T value;
    private ValueType type;

    public T calculate(T value) {
        return operatorType.operate(value, this);
    }
}
