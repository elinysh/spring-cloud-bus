package com.linecorp.healthcare.survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.navercorp.nclavis.config.EnableNClavis;

@EnableNClavis
@SpringBootApplication
public class SurveyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SurveyApplication.class, args);
    }
}
