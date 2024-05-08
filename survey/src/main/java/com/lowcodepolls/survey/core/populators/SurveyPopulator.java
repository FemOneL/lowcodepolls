package com.lowcodepolls.survey.core.populators;

import com.lowcodepolls.survey.core.dto.SurveyDTO;
import com.lowcodepolls.survey.core.entity.Survey;

public interface SurveyPopulator {
    Survey populateSurvey(SurveyDTO surveyDTO);
}
