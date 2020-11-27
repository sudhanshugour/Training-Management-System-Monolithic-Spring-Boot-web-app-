package com.cognizant.entity;

import javax.validation.constraints.Size;

public class ResetPassword {
	private String userId;
	@Size(min=8)
	private String password;
	@Size(min=8)
	private String confirm;
	public ResetPassword() {
		// TODO Auto-generated constructor stub
	}
	
	public ResetPassword(String userId, @Size(min = 8) String password, @Size(min = 8) String confirm) {
		this.userId = userId;
		this.password = password;
		this.confirm = confirm;
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
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
	

}
