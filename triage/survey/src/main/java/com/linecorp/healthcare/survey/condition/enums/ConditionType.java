package com.linecorp.healthcare.survey.condition.enums;

import com.linecorp.healthcare.survey.condition.Condition;
import com.linecorp.healthcare.survey.questionnaire.domain.factor.ConditionPredicate;

import lombok.Getter;

@Getter
public enum ConditionType {
    EQUALS {
        @Override
        public <T> boolean operate(Condition<T> condition, ConditionPredicate<T> comparator) {
            return comparator.isEqualsTo(condition.getValue());
        }
    },
    NOT_EQUALS {
        @Override
        public <T> boolean operate(Condition<T> condition, ConditionPredicate<T> comparator) {
            return !comparator.isEqualsTo(condition.getValue());
        }
    },
    GREATER_THAN {
        @Override
        public <T> boolean operate(Condition<T> condition, ConditionPredicate<T> comparator) {
            return comparator.isGreaterThan(condition.getValue());
        }
    },
    EQUALS_OR_GREATER_THAN {
        @Override
        public <T> boolean operate(Condition<T> condition, ConditionPredicate<T> comparator) {
            return comparator.isEqualsOrGreaterThan(condition.getValue());
        }
    },
    LESS_THAN {
        @Override
        public <T> boolean operate(Condition<T> condition, ConditionPredicate<T> comparator) {
            return comparator.isLessThan(condition.getValue());
        }
    },
    EQUALS_OR_LESS_THAN {
        @Override
        public <T> boolean operate(Condition<T> condition, ConditionPredicate<T> comparator) {
            return comparator.isEqualsOrLessThan(condition.getValue());
        }
    };

    public abstract <T> boolean operate(Condition<T> condition, ConditionPredicate<T> comparator);
}

