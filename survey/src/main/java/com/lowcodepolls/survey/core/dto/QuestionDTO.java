package com.lowcodepolls.survey.core.dto;

import com.lowcodepolls.survey.core.dto.enums.QuestionType;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionDTO(long id, String componentId, String question, QuestionType type, List<AnswerDTO> answers) {}