package com.lowcodepolls.survey.core.repository;

import com.lowcodepolls.survey.core.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}