package com.linecorp.healthcare.survey.questionnaire.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.healthcare.survey.questionnaire.domain.Questionnaire;
import com.linecorp.healthcare.survey.questionnaire.domain.Questionnaire.Status;
import com.linecorp.healthcare.survey.questionnaire.domain.QuestionnaireResponseIncluding;
import com.linecorp.healthcare.survey.questionnaire.dto.QuestionnaireDto;
import com.linecorp.healthcare.survey.questionnaire.dto.QuestionnaireListDto;
import com.linecorp.healthcare.survey.questionnaire.service.QuestionnaireService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class QuestionnaireController {
    public static final List<QuestionnaireResponseIncluding> ALL = List.of(QuestionnaireResponseIncluding.QUESTION, QuestionnaireResponseIncluding.ANSWER);

    private final QuestionnaireService questionnaireService;

    @ApiOperation("get a questionnaire by diseaseId")
    @GetMapping(path = "/questionnaires")
    public QuestionnaireListDto getQuestionnaireByDiseaseId(@RequestParam String diseaseId,
                                                            @RequestParam List<Status> statuses) {
        return questionnaireService.getQuestionnaireByDiseaseId(diseaseId, QuestionnaireDto.sortByPublishedAt(), statuses, ALL);
    }

    @ApiOperation("get a questionnaire")
    @GetMapping("/questionnaires/{questionnaireId}")
    public QuestionnaireDto getQuestionnaireById(@PathVariable Long questionnaireId) {
        return questionnaireService.getQuestionnaireById(questionnaireId, ALL);
    }

    @ApiOperation("save a questionnaire")
    @PostMapping("/questionnaires")
    public QuestionnaireDto saveQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return questionnaireService.saveQuestionnaire(questionnaire, ALL);
    }
}
