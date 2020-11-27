package com.cognizant.entity;

import org.springframework.stereotype.Component;

@Component
public class Contact {
	private String contact;
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public Contact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	

}
