package com.linecorp.healthcare.survey.recommender.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.healthcare.exception.NotFoundException;
import com.linecorp.healthcare.survey.recommender.domain.Recommender;
import com.linecorp.healthcare.survey.recommender.repository.RecommenderRepository;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/v1")
@RequiredArgsConstructor
@RestController
public class RecommendationController {
    private final RecommenderRepository recommenderRepository;

    @ApiOperation("get recommender")
    @GetMapping("/recommenders/{applicationId}")
    public Recommender getRecommenderByApplicationId(@PathVariable String applicationId) {
        return recommenderRepository.getRecommenderByApplicationId(applicationId)
                                    .orElseThrow(() -> new NotFoundException("recommender is not founded"));
    }

    @ApiOperation("save recommender")
    @PostMapping("/recommenders")
    public Recommender getRecommender(@RequestBody Recommender recommender) {
        return recommenderRepository.save(recommender);
    }
}
