package com.linecorp.healthcare.survey.questionnaire.service;

import static java.util.function.Function.identity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.linecorp.healthcare.disease.module.mapper.ObjectMapperConverter;
import com.linecorp.healthcare.exception.NotFoundException;
import com.linecorp.healthcare.survey.questionnaire.domain.Questionnaire;
import com.linecorp.healthcare.survey.questionnaire.domain.QuestionnaireDtoAssembler;
import com.linecorp.healthcare.survey.questionnaire.domain.QuestionnaireResponseIncluding;
import com.linecorp.healthcare.survey.questionnaire.domain.question.Question;
import com.linecorp.healthcare.survey.questionnaire.dto.QuestionnaireDto;
import com.linecorp.healthcare.survey.questionnaire.dto.QuestionnaireListDto;
import com.linecorp.healthcare.survey.questionnaire.repository.QuestionnaireRepository;

@Service
public class QuestionnaireService {
    private final Map<QuestionnaireResponseIncluding, QuestionnaireDtoAssembler> assemblerMap;
    private final ObjectProvider<QuestionnaireDtoAssembler> assemblers;
    private final QuestionnaireRepository questionnaireRepository;
    private final MongoTemplate mongoOperations;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionnaireService(ObjectProvider<QuestionnaireDtoAssembler> assemblers,
                                QuestionnaireRepository questionnaireRepository,
                                MongoTemplate mongoOperations,
                                QuestionService questionService,
                                AnswerService answerService) {
        this.assemblers = assemblers;
        this.assemblerMap = assemblers.stream().collect(Collectors.toMap(QuestionnaireDtoAssembler::supports, identity()));
        this.questionnaireRepository = questionnaireRepository;
        this.mongoOperations = mongoOperations;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public QuestionnaireListDto getQuestionnaireByDiseaseId(String diseaseId,
                                                            Sort sort,
                                                            List<Questionnaire.Status> statuses,
                                                            List<QuestionnaireResponseIncluding> questionnaireResponseIncludings) {
        List<QuestionnaireDto> questionnaireDto = getQuestionnaireByApplicationId(diseaseId, sort, statuses)
                .stream()
                .map(questionnaire -> assemble(questionnaire, questionnaireResponseIncludings))
                .collect(Collectors.toList());
        return QuestionnaireListDto.builder().questionnaireDtos(questionnaireDto).build();
    }

    public QuestionnaireDto getQuestionnaireById(Long questionnaireId,
                                                 List<QuestionnaireResponseIncluding> questionnaireResponseIncludings) {
        return Stream.of(getOnlyQuestionnaire(questionnaireId))
                     .map(questionnaire -> assemble(questionnaire, questionnaireResponseIncludings))
                     .findAny().orElseThrow(() -> new NotFoundException("Questionnaire is not founded."));
    }

    @Transactional
    public QuestionnaireDto saveQuestionnaire(Questionnaire questionnaire,
                                              List<QuestionnaireResponseIncluding> questionnaireResponseIncludings) {
        questionnaireRepository.save(questionnaire);
        List<Question> questions = questionService.insertQuestions(questionnaire);
        answerService.insertAnswers(questionnaire, questions);

        return getQuestionnaireById(questionnaire.getQuestionnaireId(), questionnaireResponseIncludings);
    }

    public Questionnaire getOnlyQuestionnaire(Long questionnaireId) {
        return questionnaireRepository.findQuestionnaireByQuestionnaireId(questionnaireId)
                                      .orElseThrow(() -> new NotFoundException("id: " + questionnaireId));
    }

    private List<Questionnaire> getQuestionnaireByApplicationId(String diseaseId,
                                                                Sort sort,
                                                                List<Questionnaire.Status> statuses) {
        Query query = new Query(Criteria.where("applicationId").is(diseaseId)
                                        .andOperator(Criteria.where("status").in(statuses))).with(sort);
        List<Questionnaire> questionnaire = mongoOperations.find(query, Questionnaire.class);
        if (CollectionUtils.isEmpty(questionnaire)) {
            throw new NotFoundException("id: " + diseaseId);
        }
        return questionnaire;
    }

    private QuestionnaireDto assemble(Questionnaire questionnaire,
                                      List<QuestionnaireResponseIncluding> includings) {
        QuestionnaireDto result = ObjectMapperConverter.convert(questionnaire, QuestionnaireDto.class);
        includings.stream()
                  .map(assemblerMap::get)
                  .sorted(Comparator.comparing(QuestionnaireDtoAssembler::getOrder))
                  .forEach(assembler -> assembler.assemble(result));
        return result;
    }
}
