package com.lowcodepolls.survey.core.repository;

import com.lowcodepolls.survey.core.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}