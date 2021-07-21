package com.linecorp.healthcare.triage.condition;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.triage.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonTypeName(ValueType.TEXT_NAME)
@EqualsAndHashCode(callSuper = true)
public class TextCondition extends Condition<String> {
    public TextCondition() {
        super.setType(ValueType.TEXT);
    }
}
