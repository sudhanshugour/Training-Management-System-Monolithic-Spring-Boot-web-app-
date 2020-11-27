package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long feedId;
	private long courseId;
	private String userId;
	private String feedback;
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(long courseId, String userId, String feedback) {
		super();
		this.courseId = courseId;
		this.userId = userId;
		this.feedback = feedback;
	}
	public long getFeedId() {
		return feedId;
	}
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Override
	public String toString() {
		return "Feedback [feedId=" + feedId + ", courseId=" + courseId + ", userId=" + userId + ", feedback=" + feedback
				+ "]";
	}
	
	
}
