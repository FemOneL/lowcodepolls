package com.lowcodepolls.survey.core.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SurveyDTO(long id, List<QuestionDTO> questions) {}