package com.lowcodepolls.survey.core.populators.impl;

import com.lowcodepolls.survey.core.dto.AnswerDTO;
import com.lowcodepolls.survey.core.dto.QuestionDTO;
import com.lowcodepolls.survey.core.dto.SurveyDTO;
import com.lowcodepolls.survey.core.entity.Answer;
import com.lowcodepolls.survey.core.entity.Question;
import com.lowcodepolls.survey.core.entity.Survey;
import com.lowcodepolls.survey.core.populators.SurveyPopulator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultSurveyPopulator implements SurveyPopulator {

    @Override
    public Survey populateSurvey(SurveyDTO surveyDTO) {
        List<Question> questions = new ArrayList<>();
        surveyDTO.questions()
                .forEach(questionDTO -> questions.add(populateQuestion(questionDTO)));

        Survey survey = Survey.builder()
                .id(surveyDTO.id())
                .isDraft(surveyDTO.isDraft())
                .questions(questions)
                .build();

        questions.forEach(question -> question.setSurvey(survey));

        return survey;
    }

    private Answer populateAnswer(AnswerDTO answerDTO) {
        return Answer.builder()
                .id(answerDTO.id())
                .answer(answerDTO.answer())
                .build();
    }

    private Question populateQuestion(QuestionDTO questionDTO) {
        List<Answer> answers = new ArrayList<>();

        questionDTO.answers()
                .forEach(answerDTO -> answers.add(populateAnswer(answerDTO)));

        Question question = Question.builder()
                .id(questionDTO.id())
                .componentId(questionDTO.componentId())
                .type(questionDTO.type())
                .question(questionDTO.question())
                .answers(answers)
                .build();

        answers.forEach(answer -> answer.setQuestion(question));

        return question;
    }
}
