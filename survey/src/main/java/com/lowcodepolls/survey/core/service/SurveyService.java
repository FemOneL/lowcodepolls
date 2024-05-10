package com.lowcodepolls.survey.core.service;

import com.lowcodepolls.survey.core.dto.SurveyDTO;
import com.lowcodepolls.survey.core.entity.Survey;

public interface SurveyService {
    Survey saveSurvey(SurveyDTO surveyDTO);

    SurveyDTO getSurvey(long surveyId);
}
