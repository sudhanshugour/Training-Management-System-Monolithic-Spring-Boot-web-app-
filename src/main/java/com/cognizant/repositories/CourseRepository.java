package com.cognizant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.cognizant.model.Course;

//import com.tms.pojo.AssessmentRecord;


public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByCourseIdIn(List<Long> ids);
	List<Course> findByCourseNameIgnoreCase(String name);
	

	Iterable<Course> findByUserId(String attribute);
	Course findByCourseId(long courseId);
	List<Course> findByCourseNameLikeIgnoreCase(String courseName);
	
}
