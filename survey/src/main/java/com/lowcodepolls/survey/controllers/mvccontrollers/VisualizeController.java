package com.lowcodepolls.survey.controllers.mvccontrollers;

import com.lowcodepolls.survey.core.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/show")
public class VisualizeController {

    private final SurveyService surveyService;

    @GetMapping("/{surveyId}/survey")
    public ModelAndView view(@PathVariable long surveyId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("survey");
        modelAndView.addObject("survey", surveyService.getSurvey(surveyId));
        return modelAndView;
    }

}
