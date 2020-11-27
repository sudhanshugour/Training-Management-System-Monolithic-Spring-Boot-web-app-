package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.cognizant.model.Skill;

//import com.cognizant.model.QuestionAndAnswer;

public interface SkillRepository extends JpaRepository<Skill, Long> {

	
	List<Skill> findByCourseId(long courseId);
	List<Skill> findBySkillNameLikeIgnoreCase(String name);
	Skill findBySkillId(long skillId);
	Skill findByCourseIdAndSkillNameIgnoreCase(long courseId,String skill);
	
	
}