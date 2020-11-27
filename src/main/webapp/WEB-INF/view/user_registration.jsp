<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
	<title>Bootstrap Simple Registration Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body background="plain_bg.jpg">
	<div class="signup-form container">
		<sf:form action="userRegistration" modelAttribute="person" method="post">
			<h2>Register</h2>
			<p class="hint-text">Create your account. It's free and only takes a minute.</p>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-6"><sf:input type="text" class="form-control" name="firstName"
							placeholder="First Name" path="firstName" id="firstName" required="true" pattern="[A-Za-z ]{1,}" message="In First Name only letters are allowed"/></div>
					<div class="col-xs-6"><sf:input type="text" class="form-control" name="lastName"
							placeholder="Last Name" path="lastName" id="lastName" required="true" pattern="[A-Za-z ]{1,}" message="In Last Name only letters are allowed"/></div>
				</div>
				<div class="row">
					<div class ="col-xs-6">	<sf:errors path="firstName"></sf:errors></div>
					<div class ="col-xs-6"><sf:errors path="lastName"></sf:errors></div>
				</div>
			</div>
			<div class="form-group">
				<sf:input path="age" class="form-control" name="age" placeholder="Age" required="true" pattern="[1-9]{1}[0-9]{0,3}" message="Invalid age only digits allowed"/>
				<div><sf:errors path="age"></sf:errors> </div>
					
			</div>
			<div class="form-group">
				&nbsp;
				<sf:radiobutton path="gender" name="gender" value="male" message="Select Your Gender" required="true" /> Male &nbsp;
				<sf:radiobutton  path="gender" name="gender" value="female" message="Select Your Gender" required="true"/> Female &nbsp;
				<sf:radiobutton  path="gender" name="gender" value="others" message="Select Your Gender" required="true"/> Others
			<div><sf:errors path="gender"></sf:errors></div>
			</div>
			
			<div class="form-group">
				<sf:input type="text" class="form-control" name="contactNumber"  path="contactNumber" placeholder="Phone Number"  required="true" pattern="[6-9]{1}[0-9]{9}" message="Invalid Contact number"/>
					<div><sf:errors path="contactNumber"></sf:errors></div>
			</div>
			<div class="form-group">
				<sf:input type="text" class="form-control" name="userId"  path="userId" placeholder="User Id" message ="Invalid Email Id" required="true" pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"/>
			     <div><label style="color:red">${par}</label><sf:errors path="userId"></sf:errors></div>
			</div>
			<div class="form-group">
				<sf:password class="form-control" name="password" path="password" placeholder="Password (minimum length 8)" message="Enter atleast 1 uppercase, 1 lowercase and 1 special Symbol, minimum 8 characters" required="true" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$"/>
			    <div><sf:errors path="password"></sf:errors></div>
			</div>
		  <div class="form-group">
				<sf:hidden  class="form-control" name="role" path="role" value="employee"/>
			</div>
			<div class="form-group">
				<sf:select path="qid1" style=" max-width: 100%;" name="qid1" items="${secretQuestionsList}" />
				<sf:input path="ans1" class="form-control" name="ans1" required="true" placeholder="Answer"/>
				   <div><sf:errors path="ans1"></sf:errors></div>
			</div>
			<div class="form-group">
				<sf:select path="qid2" style=" max-width: 100%;" name="qid2" items="${secretQuestionsList}" />
				<sf:input path="ans2" class="form-control" name="ans2" required="true" placeholder="Answer"/>
				   <div><sf:errors path="ans2"></sf:errors></div>
			</div>
			<div class="form-group">
				<sf:select path="qid3" style=" max-width: 100%;" name="qid3" items="${secretQuestionsList}" />
				<sf:input path="ans3" class="form-control" name="ans3" required="true"  placeholder="Answer"/>
				<div><sf:errors path="ans3"></sf:errors></div>
			</div>
			
			<div class="form-group">
				<button type="submit" class="btn btn-success btn-lg btn-block">Register</button>
			
				<label style="color:red">${para}</label>
			</div>
			<div class="text-center">Already have an account? <a href="login">Log In</a></div>
		</sf:form>
		
	</div>
</body>

</html>