package com.linecorp.healthcare.survey.questionnaire.domain;

import com.linecorp.healthcare.survey.questionnaire.dto.QuestionnaireDto;

public interface QuestionnaireDtoAssembler {
    QuestionnaireResponseIncluding supports();

    QuestionnaireDto assemble(QuestionnaireDto dto);

    int getOrder();
}
