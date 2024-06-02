package com.lowcodepolls.surveyView.core.dto;

import lombok.Builder;
import lombok.Setter;

import java.util.List;

@Builder
public record SurveyDTO(long id, boolean isDraft, List<QuestionDTO> questions) {}