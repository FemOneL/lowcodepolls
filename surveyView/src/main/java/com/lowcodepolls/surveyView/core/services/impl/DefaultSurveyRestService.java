package com.lowcodepolls.surveyView.core.services.impl;

import com.lowcodepolls.surveyView.core.dto.SurveyDTO;
import com.lowcodepolls.surveyView.core.exceptions.SurveyNotFoundException;
import com.lowcodepolls.surveyView.core.services.SurveyRestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultSurveyRestService implements SurveyRestService {

    private static final String GET_URL = "http://localhost:8080/surveys/%d/survey";
    private static final String PUBLISH = "/publish";

    @Override
    public SurveyDTO getSurvey(long surveyId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(String.format(GET_URL, surveyId), SurveyDTO.class);
        } catch (Exception e) {
            throw new SurveyNotFoundException();
        }
    }

    @Override
    public void publishEvent(long surveyId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForObject(String.format(GET_URL + PUBLISH, surveyId), null, SurveyDTO.class);
        } catch (Exception e) {
            throw new SurveyNotFoundException();
        }
    }
}
