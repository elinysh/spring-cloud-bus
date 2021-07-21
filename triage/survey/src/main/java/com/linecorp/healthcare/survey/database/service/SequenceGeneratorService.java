package com.linecorp.healthcare.survey.database.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.linecorp.healthcare.survey.database.domain.DatabaseSequence;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SequenceGeneratorService {
    private final MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true), DatabaseSequence.class);
        return counter.getSeq();
    }
}
