package com.linecorp.healthcare.triage.config.mongo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.linecorp.healthcare.triage.TriageApplication;

@Configuration
@EnableMongoRepositories(basePackageClasses = TriageApplication.class)
@EnableMongoAuditing
public class MongoConfiguration {
}

