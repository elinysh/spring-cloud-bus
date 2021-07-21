package com.linecorp.healthcare.survey.questionnaire.domain.factor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import com.linecorp.healthcare.survey.questionnaire.domain.answer.ValueType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
                      @Type(value = DoubleFactor.class, name = ValueType.DOUBLE_NAME),
                      @Type(value = TextFactor.class, name = ValueType.TEXT_NAME),
                      @Type(value = CodeFactor.class, name = ValueType.CODE_NAME)
              })
public abstract class Factor<T> implements ConditionPredicate<T> {
    private String code;
    private String name;
    private T value;
    private ValueType type;
}
