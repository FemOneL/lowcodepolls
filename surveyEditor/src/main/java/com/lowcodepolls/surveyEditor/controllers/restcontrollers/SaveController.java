package com.lowcodepolls.surveyEditor.controllers.restcontrollers;

import com.lowcodepolls.surveyEditor.core.dto.SurveyDTO;
import com.lowcodepolls.surveyEditor.core.services.impl.DefaultSurveyRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/save")
@RequiredArgsConstructor
public class SaveController {

    private final DefaultSurveyRestService surveyRestService;

    @PostMapping
    public SurveyDTO saveElement(@RequestBody SurveyDTO surveyDTO) {
        return surveyRestService.saveSurvey(surveyDTO);
    }

}
