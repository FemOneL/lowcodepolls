package com.lowcodepolls.survey.core.dto;

import lombok.Builder;

@Builder
public record AnswerDTO(long id, String answer) {}
