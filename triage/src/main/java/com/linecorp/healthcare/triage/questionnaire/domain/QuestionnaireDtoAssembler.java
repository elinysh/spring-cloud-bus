package com.linecorp.healthcare.triage.questionnaire.domain;

import com.linecorp.healthcare.triage.questionnaire.dto.QuestionnaireDto;

public interface QuestionnaireDtoAssembler {
    QuestionnaireResponseIncluding supports();

    QuestionnaireDto assemble(QuestionnaireDto dto);

    int getOrder();
}
