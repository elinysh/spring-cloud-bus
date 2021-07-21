package com.linecorp.healthcare.survey.questionnaire.domain.answer;

import static com.linecorp.healthcare.survey.questionnaire.domain.question.QuestionAssembler.QUESTION_ORDER;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.linecorp.healthcare.disease.module.mapper.ObjectMapperConverter;
import com.linecorp.healthcare.survey.questionnaire.domain.QuestionnaireDtoAssembler;
import com.linecorp.healthcare.survey.questionnaire.domain.QuestionnaireResponseIncluding;
import com.linecorp.healthcare.survey.questionnaire.dto.AnswerDto;
import com.linecorp.healthcare.survey.questionnaire.dto.QuestionnaireDto;
import com.linecorp.healthcare.survey.questionnaire.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AnswerAssembler implements QuestionnaireDtoAssembler {
    public static final int ANSWER_ORDER = QUESTION_ORDER + 100;
    private final AnswerRepository answerRepository;

    @Override
    public QuestionnaireResponseIncluding supports() {
        return QuestionnaireResponseIncluding.ANSWER;
    }

    @Override
    public QuestionnaireDto assemble(QuestionnaireDto questionnaireDto) {
        Map<Long, List<AnswerDto>> answerMap = getAnswerMap(List.of(questionnaireDto.getQuestionnaireId()));

        questionnaireDto.getQuestions().forEach(questionDto -> {
            questionDto.setAnswers(answerMap.get(questionDto.getQuestionId()));
        });

        return questionnaireDto;
    }

    private Map<Long, List<AnswerDto>> getAnswerMap(List<Long> questionnaireIds) {
        List<Answer> answers = answerRepository.findAnswersByQuestionnaireIdIn(questionnaireIds);
        Map<Long, List<AnswerDto>> answerMap = answers
                .stream()
                .map(answer -> ObjectMapperConverter.convert(answer, AnswerDto.class))
                .collect(Collectors.groupingBy(AnswerDto::getQuestionId));

        answerMap.forEach((key, value) -> value.sort(Comparator.comparing(AnswerDto::getOrder)));

        return answerMap;
    }

    @Override
    public int getOrder() {
        return ANSWER_ORDER;
    }
}
