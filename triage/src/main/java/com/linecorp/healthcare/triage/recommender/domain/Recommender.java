package com.linecorp.healthcare.triage.recommender.domain;


import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.linecorp.healthcare.triage.condition.Condition;
import com.linecorp.healthcare.triage.database.domain.AutoIncreasable;

import lombok.Data;

@Data
@Document(collection = "recommenders")
public class Recommender implements AutoIncreasable {
    @Id
    private Long recommenderId;
    @Indexed
    private String applicationId;
    private Map<Long, Map<String, Condition>> questionnaireConditionTable;

    @Override
    public void setId(Long id) {
        this.recommenderId = id;
    }
}
