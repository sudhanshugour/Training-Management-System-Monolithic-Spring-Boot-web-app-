package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class UserAssessment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long UserAssessmentId;
	private long skillId;
	private String userId;
	private short attempts;
	private short marks;
	private short totalMarks;
	private String topicCompltetd;
	public long getUserAssessmentId() {
		return UserAssessmentId;
	}
	
	
	public UserAssessment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserAssessment(long userAssessmentId, long skillId, String userId, short attempts, short marks,
			short totalMarks, String topicCompltetd) {
		super();
		UserAssessmentId = userAssessmentId;
		this.skillId = skillId;
		this.userId = userId;
		this.attempts = attempts;
		this.marks = marks;
		this.totalMarks = totalMarks;
		this.topicCompltetd = topicCompltetd;
	}


	public void setUserAssessmentId(long userAssessmentId) {
		UserAssessmentId = userAssessmentId;
	}
	public long getSkillId() {
		return skillId;
	}
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public short getAttempts() {
		return attempts;
	}
	public void setAttempts(short attempts) {
		this.attempts = attempts;
	}
	public short getMarks() {
		return marks;
	}
	public void setMarks(short marks) {
		this.marks = marks;
	}
	public short getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(short totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getTopicCompltetd() {
		return topicCompltetd;
	}
	public void setTopicCompltetd(String topicCompltetd) {
		this.topicCompltetd = topicCompltetd;
	}
	@Override
	public String toString() {
		return "UserAssessment [UserAssessmentId=" + UserAssessmentId + ", skillId=" + skillId + ", userId=" + userId
				+ ", attempts=" + attempts + ", marks=" + marks + ", totalMarks=" + totalMarks + ", topicCompltetd="
				+ topicCompltetd + "]";
	}
	
	

}
