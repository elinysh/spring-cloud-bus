package com.linecorp.healthcare.triage.database.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "databaseSequences")
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;
}
