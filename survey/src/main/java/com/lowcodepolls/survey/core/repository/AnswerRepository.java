package com.lowcodepolls.survey.core.repository;

import com.lowcodepolls.survey.core.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}