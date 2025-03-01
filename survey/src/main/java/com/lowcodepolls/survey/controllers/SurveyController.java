package com.lowcodepolls.survey.controllers;

import com.lowcodepolls.survey.core.dto.SurveyDTO;
import com.lowcodepolls.survey.core.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public SurveyDTO createSurvey(@RequestBody SurveyDTO surveyDTO) {
        return surveyService.saveSurvey(surveyDTO);
    }

    @PostMapping("/{surveyId}/survey/publish")
    public void publishSurvey(@PathVariable long surveyId) {
        surveyService.publishSurvey(surveyId);
    }

    @GetMapping("/{surveyId}/survey")
    public SurveyDTO getSurvey(@PathVariable long surveyId) {
        return surveyService.getSurvey(surveyId);
    }

}
