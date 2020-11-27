package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cognizant.model.QuestionAndAnswer;


public interface QuestionAndAnswerRepository extends JpaRepository<QuestionAndAnswer, Long> {
	List<QuestionAndAnswer> findBySkillId(long skillId);
}
