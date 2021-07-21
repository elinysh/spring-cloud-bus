package com.linecorp.healthcare.survey.evaluation.domain;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.linecorp.healthcare.survey.database.domain.AutoIncreasable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "resultTemplates")
@NoArgsConstructor
public class ResultTemplate implements AutoIncreasable {
    @Id
    private Long id;
    private String title;
    private String description;
    private Long questionnaireId;
    private String resultCode;
    private Map<String, Object> data;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt;
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedAt;
}
