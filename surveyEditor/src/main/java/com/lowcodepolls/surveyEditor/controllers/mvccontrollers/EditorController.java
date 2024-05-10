package com.lowcodepolls.surveyEditor.controllers.mvccontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowcodepolls.surveyEditor.core.exceptions.SurveyNotFoundException;
import com.lowcodepolls.surveyEditor.core.services.impl.DefaultSurveyRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editor")
@RequiredArgsConstructor
public class EditorController {

    private final DefaultSurveyRestService surveyRestService;

    @GetMapping("/new")
    public String newSurvey() {
        return "index";
    }


    @GetMapping("/{surveyId}/edit")
    public String edit(@PathVariable long surveyId, Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String editData;
        try {
           editData = objectMapper.writeValueAsString(surveyRestService.getSurvey(surveyId));
        } catch (SurveyNotFoundException e) {
            return "surveyNotFound";
        }

        model.addAttribute("editData", editData);
        return "index";
    }

}
