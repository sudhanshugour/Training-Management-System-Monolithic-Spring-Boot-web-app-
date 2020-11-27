package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	Feedback findByUserIdAndCourseId(String userId, long l);
	List<Feedback> findByCourseId(long courseId);
	
}
