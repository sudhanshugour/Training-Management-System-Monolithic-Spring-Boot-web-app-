package com.cognizant.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Embeddable
public class Skill {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long skillId;
	private long courseId;
	public String skillName;
	public long getSkillId() {
		return skillId;
	}
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", courseId=" + courseId + ", skillName=" + skillName + "]";
	}
	public Skill(long courseId, String skillName) {
		super();
		this.courseId = courseId;
		this.skillName = skillName;
	}
	public Skill() {
	
	}
	
	

}
