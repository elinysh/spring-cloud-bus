package com.linecorp.healthcare.triage.questionnaire.domain.question;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.QuestionnaireDtoAssembler;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.domain.QuestionnaireResponseIncluding;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.dto.QuestionDto;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.dto.QuestionnaireDto;
import com.linecorp.healthcare.disease.microservice.survey.questionnaire.repository.QuestionRepository;
import com.linecorp.healthcare.disease.module.mapper.ObjectMapperConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class QuestionAssembler implements QuestionnaireDtoAssembler {
    public static final TypeReference<List<QuestionDto>> QUESTION_DTO_LIST = new TypeReference<>() {};
    public static final int QUESTION_ORDER = 0;

    private final QuestionRepository questionRepository;

    @Override
    public QuestionnaireResponseIncluding supports() {
        return QuestionnaireResponseIncluding.QUESTION;
    }

    @Override
    public QuestionnaireDto assemble(QuestionnaireDto questionnaireDto) {
        Map<Long, List<Question>> questionMap = questionRepository.findQuestionsByQuestionnaireIdIn(List.of(questionnaireDto.getQuestionnaireId()))
                                                                  .stream()
                                                                  .sorted(Comparator.comparing(Question::getOrder))
                                                                  .collect(Collectors.groupingBy(Question::getQuestionnaireId));

        List<Question> questions = questionMap.get(questionnaireDto.getQuestionnaireId());
        List<QuestionDto> questionDtos = ObjectMapperConverter.convert(questions, QUESTION_DTO_LIST);
        questionnaireDto.transfer(questionDtos);
        return questionnaireDto;
    }

    @Override
    public int getOrder() {
        return QUESTION_ORDER;
    }
}
