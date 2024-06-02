package com.lowcodepolls.surveyEditor.core.services.impl;

import com.lowcodepolls.surveyEditor.core.dto.SurveyDTO;
import com.lowcodepolls.surveyEditor.core.exceptions.SurveyNotFoundException;
import com.lowcodepolls.surveyEditor.core.services.SurveyRestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultSurveyRestService implements SurveyRestService {

    private static final String SAVE_URL = "http://localhost:8080/surveys";
    private static final String GET_URL = "http://localhost:8080/surveys/%d/survey";

    @Override
    public SurveyDTO saveSurvey(SurveyDTO survey) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SurveyDTO> requestEntity = new HttpEntity<>(survey, headers);

        return restTemplate.postForObject(SAVE_URL, requestEntity, SurveyDTO.class);
    }

    @Override
    public SurveyDTO getSurvey(long surveyId) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(String.format(GET_URL, surveyId), SurveyDTO.class);
        } catch (Exception e) {
            throw new SurveyNotFoundException();
        }
    }
}
