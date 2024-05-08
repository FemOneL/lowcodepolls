package com.lowcodepolls.survey.core.populators.impl;

import com.lowcodepolls.survey.core.dto.AnswerDTO;
import com.lowcodepolls.survey.core.dto.QuestionDTO;
import com.lowcodepolls.survey.core.dto.SurveyDTO;
import com.lowcodepolls.survey.core.entity.Answer;
import com.lowcodepolls.survey.core.entity.Question;
import com.lowcodepolls.survey.core.entity.Survey;
import com.lowcodepolls.survey.core.populators.SurveyReversePopulator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultSurveyReversePopulator implements SurveyReversePopulator {

    @Override
    public SurveyDTO populateSurvey(Survey survey) {
        List<QuestionDTO> questions = new ArrayList<>();
        survey.getQuestions()
                .forEach(question -> questions.add(populateQuestion(question)));

        return SurveyDTO.builder()
                .id(survey.getId())
                .questions(questions)
                .build();
    }

    private AnswerDTO populateAnswer(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .answer(answer.getAnswer())
                .build();
    }

    private QuestionDTO populateQuestion(Question question) {
        List<AnswerDTO> answers = new ArrayList<>();

        question.getAnswerOptions()
                .forEach(answer -> answers.add(populateAnswer(answer)));

        return QuestionDTO.builder()
                .id(question.getId())
                .componentId(question.getComponentId())
                .type(question.getType())
                .question(question.getQuestion())
                .answers(answers)
                .build();
    }


}
