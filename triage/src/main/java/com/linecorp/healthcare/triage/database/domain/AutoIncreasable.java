package com.linecorp.healthcare.triage.database.domain;

public interface AutoIncreasable {
    void setId(Long id);

    default String sequenceName() {
        return this.getClass().getSimpleName().toLowerCase() + "s";
    }
}
