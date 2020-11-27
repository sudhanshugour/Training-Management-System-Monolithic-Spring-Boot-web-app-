package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long courseId;
	private String userId;
	private String courseName;
	private String courseDetail;
	private String competencyLevel;
	private String intendedAudience;
	private String prerequisites;
	private int duration;
	private String feedbackQue;
	public String getFeedbackQue() {
		return feedbackQue;
	}
	public void setFeedbackQue(String feedbackQue) {
		this.feedbackQue = feedbackQue;
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDetail() {
		return courseDetail;
	}
	public void setCourseDetail(String courseDetail) {
		this.courseDetail = courseDetail;
	}
	public String getCompetencyLevel() {
		return competencyLevel;
	}
	public void setCompetencyLevel(String competencyLevel) {
		this.competencyLevel = competencyLevel;
	}
	public String getIntendedAudience() {
		return intendedAudience;
	}
	public void setIntendedAudience(String intendedAudience) {
		this.intendedAudience = intendedAudience;
	}
	public String getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(String prerequisites) {
		this.prerequisites = prerequisites;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(long courseId, String userId, String courseName, String courseDetail, String competencyLevel,
			String intendedAudience, String prerequisites, int duration,String feedbackQue) {
		super();
		this.courseId = courseId;
		this.userId = userId;
		this.courseName = courseName;
		this.courseDetail = courseDetail;
		this.competencyLevel = competencyLevel;
		this.intendedAudience = intendedAudience;
		this.prerequisites = prerequisites;
		this.duration = duration;
		this.feedbackQue = feedbackQue;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", userId=" + userId + ", courseName=" + courseName + ", courseDetail="
				+ courseDetail + ", competencyLevel=" + competencyLevel + ", intendedAudience=" + intendedAudience
				+ ", prerequisites=" + prerequisites + ", duration=" + duration + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((competencyLevel == null) ? 0 : competencyLevel.hashCode());
		result = prime * result + ((courseDetail == null) ? 0 : courseDetail.hashCode());
		result = prime * result + (int) (courseId ^ (courseId >>> 32));
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + duration;
		result = prime * result + ((intendedAudience == null) ? 0 : intendedAudience.hashCode());
		result = prime * result + ((prerequisites == null) ? 0 : prerequisites.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (competencyLevel == null) {
			if (other.competencyLevel != null)
				return false;
		} else if (!competencyLevel.equals(other.competencyLevel))
			return false;
		if (courseDetail == null) {
			if (other.courseDetail != null)
				return false;
		} else if (!courseDetail.equals(other.courseDetail))
			return false;
		if (courseId != other.courseId)
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (duration != other.duration)
			return false;
		if (intendedAudience == null) {
			if (other.intendedAudience != null)
				return false;
		} else if (!intendedAudience.equals(other.intendedAudience))
			return false;
		if (prerequisites == null) {
			if (other.prerequisites != null)
				return false;
		} else if (!prerequisites.equals(other.prerequisites))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	

}