package com.lowcodepolls.surveyEditor.core.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SurveyDTO(long id, List<QuestionDTO> questions) {}
