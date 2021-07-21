package com.linecorp.healthcare.survey.database.domain;

public interface AutoIncreasable {
    void setId(Long id);

    default String sequenceName() {
        return this.getClass().getSimpleName().toLowerCase() + "s";
    }
}
