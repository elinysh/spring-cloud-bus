package com.linecorp.healthcare.triage.questionnaire.domain.answer.descision;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Decision {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<FactorModifier> factorModifiers;
    private Action action;
}
