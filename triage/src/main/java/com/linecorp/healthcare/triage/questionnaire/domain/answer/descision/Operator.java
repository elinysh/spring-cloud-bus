package com.linecorp.healthcare.triage.questionnaire.domain.answer.descision;

public interface Operator<T> {
    T getValue();

    T add(T t);

    T subtraction(T t);
}
