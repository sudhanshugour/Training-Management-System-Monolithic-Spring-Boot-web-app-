package com.cognizant.entity;

import org.springframework.stereotype.Component;

@Component
public class ForgotUserId {
	private String contact;
	private String ans1;
	private String ans2;
	private String ans3;
	public ForgotUserId() {
		// TODO Auto-generated constructor stub
	}
	public ForgotUserId( String contact,String ans1, String ans2, String ans3) {
		
		this.contact = contact;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getAns3() {
		return ans3;
	}
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	

}
