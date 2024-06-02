package com.lowcodepolls.surveyView.controllers;

import com.lowcodepolls.surveyView.core.dto.QuestionDTO;
import com.lowcodepolls.surveyView.core.dto.SurveyDTO;
import com.lowcodepolls.surveyView.core.services.SurveyRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view/{surveyId}")
public class SurveyViewController {

    private final SurveyRestService surveyRestService;

    @GetMapping("/survey")
    public ModelAndView getView(@PathVariable long surveyId) {
        ModelAndView modelAndView = new ModelAndView();
        SurveyDTO survey = surveyRestService.getSurvey(surveyId);
        survey.questions().sort((q1, q2) -> {
            int numA = Integer.parseInt(String.valueOf(q1.componentId().charAt(q1.componentId().length() - 1)));
            int numB = Integer.parseInt(String.valueOf(q2.componentId().charAt(q2.componentId().length() - 1)));

            return Integer.compare(numA, numB);
        });

        modelAndView.setViewName("surveyView");
        modelAndView.addObject("survey", survey);
        return modelAndView;
    }

    @PostMapping("/survey/publish")
    public void publish(@PathVariable long surveyId) {
        surveyRestService.publishEvent(surveyId);
    }

}
