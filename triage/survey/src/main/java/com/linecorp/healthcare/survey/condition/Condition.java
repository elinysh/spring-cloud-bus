package com.linecorp.healthcare.survey.condition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import com.linecorp.healthcare.survey.questionnaire.domain.answer.ValueType;
import com.linecorp.healthcare.survey.condition.enums.ConditionType;
import com.linecorp.healthcare.survey.questionnaire.domain.factor.Factor;

import lombok.Data;
import lombok.Getter;

@Data
@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonSubTypes({
                      @Type(value = DoubleCondition.class, name = ValueType.DOUBLE_NAME),
                      @Type(value = TextCondition.class, name = ValueType.TEXT_NAME),
                      @Type(value = CodeCondition.class, name = ValueType.CODE_NAME)
              })
public abstract class Condition<T> {
    protected TargetType targetType;
    protected ConditionType conditionType;
    protected T value;
    protected Condition<?> and;
    protected Condition<?> or;
    protected ValueType type;

    public boolean matches(Factor factor) {
        return matchesThis(factor) && matchesNext(factor);
    }

    protected boolean matchesThis(Factor factor) {
        return factor != null && this.conditionType.operate(this, factor);
    }

    private boolean matchesNext(Factor factor) {
        if (and == null && or == null) {
            return true;
        }
        if (and != null && and.matches(factor)) {
            return true;
        }
        return or != null && or.matches(factor);
    }

    @Getter
    public enum TargetType {
        ANSWER, FACTOR, USER_PROFILE;
    }
}
