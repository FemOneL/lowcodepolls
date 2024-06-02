package com.lowcodepolls.survey.core.service;

import com.lowcodepolls.survey.core.dto.SurveyDTO;
import com.lowcodepolls.survey.core.entity.Survey;

public interface SurveyService {
    SurveyDTO saveSurvey(SurveyDTO surveyDTO);

    void publishSurvey(long surveyId);

    SurveyDTO getSurvey(long surveyId);
}
