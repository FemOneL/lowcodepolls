package com.lowcodepolls.survey.core.exceptions;

public class SurveyNotFoundException extends RuntimeException{
    public SurveyNotFoundException() {
    }

    public SurveyNotFoundException(String message) {
        super(message);
    }
}
