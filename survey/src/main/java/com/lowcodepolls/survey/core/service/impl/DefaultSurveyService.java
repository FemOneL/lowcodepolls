package com.lowcodepolls.survey.core.service.impl;

import com.lowcodepolls.survey.core.dto.SurveyDTO;
import com.lowcodepolls.survey.core.entity.Survey;
import com.lowcodepolls.survey.core.exceptions.SurveyNotFoundException;
import com.lowcodepolls.survey.core.populators.SurveyPopulator;
import com.lowcodepolls.survey.core.populators.SurveyReversePopulator;
import com.lowcodepolls.survey.core.repository.SurveyRepository;
import com.lowcodepolls.survey.core.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultSurveyService implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final SurveyPopulator surveyPopulator;

    private final SurveyReversePopulator surveyReversePopulator;

    @Override
    public SurveyDTO saveSurvey(SurveyDTO surveyDTO) {
        Survey survey = surveyPopulator.populateSurvey(surveyDTO);
        return surveyReversePopulator.populateSurvey(surveyRepository.save(survey));
    }

    @Override
    public void publishSurvey(long surveyId) {
        Optional<Survey> updatingSurvey = surveyRepository.findById(surveyId);
        updatingSurvey.ifPresent(survey -> {
            survey.setIsDraft(false);
            surveyRepository.save(survey);
        });
    }

    @Override
    public SurveyDTO getSurvey(long surveyId) {
        return surveyReversePopulator.populateSurvey(surveyRepository.findById(surveyId).orElseThrow(SurveyNotFoundException::new));
    }
}
