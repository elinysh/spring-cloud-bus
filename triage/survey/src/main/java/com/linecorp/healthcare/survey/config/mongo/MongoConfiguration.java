package com.linecorp.healthcare.survey.config.mongo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.linecorp.healthcare.survey.SurveyApplication;

@Configuration
@EnableMongoRepositories(basePackageClasses = SurveyApplication.class)
@EnableMongoAuditing
public class MongoConfiguration {
}

