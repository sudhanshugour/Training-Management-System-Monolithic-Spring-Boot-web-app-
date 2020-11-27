package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int enId;
	private String userId;
	private long courseId;
	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Enrollment(String userId, long courseId) {
		super();
		this.userId = userId;
		this.courseId = courseId;
	}
	
	public int getEnId() {
		return enId;
	}
	public void setEnId(int enId) {
		this.enId = enId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	
}
