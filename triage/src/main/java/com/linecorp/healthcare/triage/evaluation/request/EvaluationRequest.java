package com.linecorp.healthcare.triage.evaluation.request;

import java.util.List;
import java.util.stream.Collectors;

import com.linecorp.healthcare.triage.questionnaire.domain.answer.UserAnswer;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EvaluationRequest {

    @ApiModel
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateEvaluation {
        private long questionnaireId;
        private String applicationId;
        private long userId;
        private List<UserSimpleAnswer> userAnswers;

        public List<UserAnswer> convertUserAnswer() {
            return userAnswers.stream()
                              .map(userSimpleAnswer -> UserAnswer.builder()
                                                                 .questionnaireId(questionnaireId)
                                                                 .questionId(userSimpleAnswer.getQuestionId())
                                                                 .userId(userId)
                                                                 .answerId(userSimpleAnswer.getAnswerId())
                                                                 .build())
                              .collect(Collectors.toList());
        }

        public List<Long> convertAnswerIds() {
            return userAnswers.stream().map(UserSimpleAnswer::getAnswerId).collect(Collectors.toList());
        }
    }

    @Data
    public static class UserSimpleAnswer {
        private long questionId;
        private long answerId;
    }
}
