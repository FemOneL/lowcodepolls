package com.lowcodepolls.surveyView.core.dto;

import com.lowcodepolls.surveyView.core.dto.enums.QuestionType;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionDTO(long id, String componentId, String question, QuestionType type, List<AnswerDTO> answers) {}