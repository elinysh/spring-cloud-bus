package com.linecorp.healthcare.disease.module.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectMapperConverter {
    private static ObjectMapper objectMapper;

    public void initObjectMapper(ObjectMapper objectMapper) {
        ObjectMapperConverter.objectMapper = objectMapper;
    }

    public <T, R> R convert(T t, Class<R> type) {
        return objectMapper.convertValue(t, type);
    }

    public <T, R> R convert(T t, TypeReference<R> type) {
        return objectMapper.convertValue(t, type);
    }
}
