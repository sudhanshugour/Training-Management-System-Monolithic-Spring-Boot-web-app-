package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.model.UserAssessment;

public interface UserAssessmentRepository extends JpaRepository< UserAssessment, Long>{
	List<UserAssessment> findByUserId(String userId);
	UserAssessment findBySkillId(long skillId);
	UserAssessment findBySkillIdAndUserId(long skillId, String attribute);
	

}
