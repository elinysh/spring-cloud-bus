package com.linecorp.healthcare.survey.condition;

import com.fasterxml.jackson.annotation.JsonTypeName;

import com.linecorp.healthcare.survey.questionnaire.domain.answer.ValueType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonTypeName(ValueType.CODE_NAME)
@EqualsAndHashCode(callSuper = true)
public class CodeCondition extends Condition<String> {
    public CodeCondition() {
        super.setType(ValueType.CODE);
    }
}
