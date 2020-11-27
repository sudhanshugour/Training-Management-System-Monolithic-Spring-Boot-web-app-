package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

	List<Enrollment> findByUserId(String userId);
	List<Enrollment> findByCourseId(long courseId);
	Enrollment findByUserIdAndCourseId(String userId, long courseId);
	
	@Query("select count(e.courseId) from Enrollment e where course_id=?1")
	long courseTakenCount(long courseId);
}
