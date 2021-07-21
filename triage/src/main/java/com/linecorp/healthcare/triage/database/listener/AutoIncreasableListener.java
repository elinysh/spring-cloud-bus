package com.linecorp.healthcare.triage.database.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.linecorp.healthcare.triage.database.domain.AutoIncreasable;
import com.linecorp.healthcare.triage.database.service.SequenceGeneratorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AutoIncreasableListener extends AbstractMongoEventListener<AutoIncreasable> {
    private final SequenceGeneratorService sequenceGeneratorService;

    public void onBeforeConvert(BeforeConvertEvent<AutoIncreasable> event) {
        AutoIncreasable source = event.getSource();
        Long id = sequenceGeneratorService.generateSequence(source.sequenceName());
        source.setId(id);
    }
}
