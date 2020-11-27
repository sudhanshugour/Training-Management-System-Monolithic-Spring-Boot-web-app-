package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class QuestionAndAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long questionAndAnswerId;
	private String question;
	private String option1;
	private boolean option1IsAnswer;
	private String option2;
	private boolean option2IsAnswer;
	private String option3;
	private boolean option3IsAnswer;
	private String option4;
	private boolean option4IsAnswer;
	private long skillId;

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public long getQuestionAndAnswerId() {
		return questionAndAnswerId;
	}

	public void setQuestionAndAnswerId(long questionAndAnswerId) {
		this.questionAndAnswerId = questionAndAnswerId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public boolean isOption1IsAnswer() {
		return option1IsAnswer;
	}

	public void setOption1IsAnswer(boolean option1IsAnswer) {
		this.option1IsAnswer = option1IsAnswer;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public boolean isOption2IsAnswer() {
		return option2IsAnswer;
	}

	public void setOption2IsAnswer(boolean option2IsAnswer) {
		this.option2IsAnswer = option2IsAnswer;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public boolean isOption3IsAnswer() {
		return option3IsAnswer;
	}

	public void setOption3IsAnswer(boolean option3IsAnswer) {
		this.option3IsAnswer = option3IsAnswer;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public boolean isOption4IsAnswer() {
		return option4IsAnswer;
	}

	public void setOption4IsAnswer(boolean option4IsAnswer) {
		this.option4IsAnswer = option4IsAnswer;
	}

	
	
	
	
	
	

	public QuestionAndAnswer(long questionAndAnswerId, String question, String option1, boolean option1IsAnswer,
			String option2, boolean option2IsAnswer, String option3, boolean option3IsAnswer, String option4,
			boolean option4IsAnswer, long skillId) {
		super();
		this.questionAndAnswerId = questionAndAnswerId;
		this.question = question;
		this.option1 = option1;
		this.option1IsAnswer = option1IsAnswer;
		this.option2 = option2;
		this.option2IsAnswer = option2IsAnswer;
		this.option3 = option3;
		this.option3IsAnswer = option3IsAnswer;
		this.option4 = option4;
		this.option4IsAnswer = option4IsAnswer;
		this.skillId = skillId;
	}

	@Override
	public String toString() {
		return "QuestionAndAnswer [questionAndAnswerId=" + questionAndAnswerId + ", question=" + question + ", option1="
				+ option1 + ", option1IsAnswer=" + option1IsAnswer + ", option2=" + option2 + ", option2IsAnswer="
				+ option2IsAnswer + ", option3=" + option3 + ", option3IsAnswer=" + option3IsAnswer + ", option4="
				+ option4 + ", option4IsAnswer=" + option4IsAnswer + ", skillId=" + skillId + "]\n";
	}

	public QuestionAndAnswer() {
	
	}

}
