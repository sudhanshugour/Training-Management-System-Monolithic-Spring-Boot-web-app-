package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long topicId;
	private long skillId;
	private String topic;
	private String content;
	private short topicNumber;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getTopicNumber() {
		return topicNumber;
	}

	public void setTopicNumber(short topicNumber) {
		this.topicNumber = topicNumber;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", skillId=" + skillId + ", topic=" + topic + ", content=" + content
				+ ", topicNumber=" + topicNumber + "]";
	}

	public Topic(long skillId, String topic, String content, short topicNumber, String url) {
		super();
		this.skillId = skillId;
		this.topic = topic;
		this.content = content;
		this.topicNumber = topicNumber;
		this.url = url;
	}
	public Topic() {
		
	}

}
