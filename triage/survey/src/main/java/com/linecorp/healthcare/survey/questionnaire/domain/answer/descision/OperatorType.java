package com.linecorp.healthcare.survey.questionnaire.domain.answer.descision;

import lombok.Getter;

@Getter
public enum OperatorType {
    ADDITION {
        @Override
        public <T> T operate(T factorValue, Operator<T> operator) {
            return operator.add(factorValue);
        }
    },
    SUBTRACTION {
        @Override
        public <T> T operate(T factorValue, Operator<T> operator) {
            return operator.subtraction(factorValue);
        }
    },
    NONE {
        @Override
        public <T> T operate(T factorValue, Operator<T> operator) {
            return factorValue;
        }
    };

    public abstract <T> T operate(T value, Operator<T> value2);
}
