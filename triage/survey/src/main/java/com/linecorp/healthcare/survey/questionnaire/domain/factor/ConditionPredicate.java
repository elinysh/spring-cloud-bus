package com.linecorp.healthcare.survey.questionnaire.domain.factor;

public interface ConditionPredicate<T> {
    T getValue();

    default boolean isEqualsTo(T t) {
        final T value = getValue();
        return value.equals(t);
    }

    boolean isGreaterThan(T t);

    default boolean isEqualsOrGreaterThan(T t) {
        return isEqualsTo(t) || isGreaterThan(t);
    }

    boolean isLessThan(T t);

    default boolean isEqualsOrLessThan(T t) {
        return isEqualsTo(t) || isLessThan(t);
    }
}
