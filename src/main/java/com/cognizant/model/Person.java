package com.cognizant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * 
 * @author 841495
 *
 */
@Entity
public class Person{
	
	
	private String firstName;
	
	private String lastName;
	
	private int age;
	@NotEmpty
	private String Gender;
	
	private String contactNumber;
	@NotEmpty
	@Id
	private String userId;
	
	private String password;
	//@EqualityCheck
	private int qid1;
	//@EqualityCheck
	private int qid2;
	//@EqualityCheck
	private int qid3;
	@NotEmpty
	private String ans1;
	@NotEmpty
	private String ans2;
	@NotEmpty
	private String ans3;
	private String role;
	public Person(){
		super();
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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
	

	public int getQid1() {
		return qid1;
	}

	public void setQid1(int qid1) {
		this.qid1 = qid1;
	}

	public int getQid2() {
		return qid2;
	}

	public void setQid2(int qid2) {
		this.qid2 = qid2;
	}

	public int getQid3() {
		return qid3;
	}

	public void setQid3(int qid3) {
		this.qid3 = qid3;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", Gender=" + Gender
				+ ", contactNumber=" + contactNumber + ", userId=" + userId + ", password=" + password + ", qid1="
				+ qid1 + ", qid2=" + qid2 + ", qid3=" + qid3 + ", ans1=" + ans1 + ", ans2=" + ans2 + ", ans3=" + ans3
				+ ", role=" + role + "]";
	}
	
	
	

	
	
	

}
