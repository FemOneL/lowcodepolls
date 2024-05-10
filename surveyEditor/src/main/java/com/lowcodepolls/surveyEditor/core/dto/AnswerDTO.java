package com.lowcodepolls.surveyEditor.core.dto;

import lombok.Builder;

@Builder
public record AnswerDTO(long id, String answer) {}
