package com.cognizant.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="secretquestions")
public class SecretQuestions {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="secqueid")
	private int secQueId;
	private String question;
	public SecretQuestions() {
		// TODO Auto-generated constructor stub
	}
	public SecretQuestions(int secQueId, String question) {
		super();
		this.secQueId = secQueId;
		this.question = question;
	}
	public int getSecQueId() {
		return secQueId;
	}
	public void setSecQueId(int secQueId) {
		this.secQueId = secQueId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	

}
