package com.lowcodepolls.surveyEditor.core.services;

import com.lowcodepolls.surveyEditor.core.dto.SurveyDTO;

public interface SurveyRestService {
    SurveyDTO saveSurvey(SurveyDTO survey);

    SurveyDTO getSurvey(long surveyId);

}
