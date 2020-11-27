package com.cognizant.model;

import org.springframework.stereotype.Component;


public class Login {

	private String userId;
	private String password;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
