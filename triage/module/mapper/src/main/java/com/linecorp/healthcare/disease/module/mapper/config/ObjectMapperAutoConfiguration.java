package com.linecorp.healthcare.disease.module.mapper.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.linecorp.healthcare.disease.module.mapper.ObjectMapperConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class ObjectMapperAutoConfiguration implements InitializingBean {
    private final ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        ObjectMapperConverter.initObjectMapper(objectMapper);
    }
}
