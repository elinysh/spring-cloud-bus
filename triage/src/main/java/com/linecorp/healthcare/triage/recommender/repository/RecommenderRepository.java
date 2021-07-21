package com.linecorp.healthcare.triage.recommender.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.linecorp.healthcare.triage.recommender.domain.Recommender;

public interface RecommenderRepository extends MongoRepository<Recommender, Long> {

    Optional<Recommender> getRecommenderByApplicationId(String applicationId);
}
