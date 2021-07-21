package com.linecorp.healthcare.triage.questionnaire.domain.answer;

import com.linecorp.healthcare.disease.microservice.survey.condition.CodeCondition;
import com.linecorp.healthcare.disease.microservice.survey.condition.Condition;
import com.linecorp.healthcare.disease.microservice.survey.condition.DoubleCondition;
import com.linecorp.healthcare.disease.microservice.survey.condition.TextCondition;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.descision.CodeFactorModifier;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.descision.DoubleFactorModifier;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.descision.FactorModifier;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.answer.descision.TextFactorModifier;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.factor.CodeFactor;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.factor.DoubleFactor;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.factor.Factor;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.factor.TextFactor;

import lombok.Getter;

@Getter
public enum ValueType {
    CODE(CodeFactor.class, CodeFactorModifier.class, CodeCondition.class),
    DOUBLE(DoubleFactor.class, DoubleFactorModifier.class, DoubleCondition.class),
    TEXT(TextFactor.class, TextFactorModifier.class, TextCondition.class);

    public static final String CODE_NAME = "CODE";
    public static final String DOUBLE_NAME = "DOUBLE";
    public static final String TEXT_NAME = "TEXT";

    private final Class<? extends Factor> factorType;
    private final Class<? extends FactorModifier> factorModifierType;
    private final Class<? extends Condition> conditionType;

    ValueType(Class<? extends Factor> factorType,
              Class<? extends FactorModifier> factorModifierType,
              Class<? extends Condition> conditionType) {
        this.conditionType = conditionType;
        this.factorModifierType = factorModifierType;
        this.factorType = factorType;
    }
}
