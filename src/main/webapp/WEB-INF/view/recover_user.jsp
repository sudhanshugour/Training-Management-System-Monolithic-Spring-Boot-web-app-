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
<body>
<div class="signup-form container">
<h3>Recover User ID</h3>
    <sf:form action="loadQuestion" modelAttribute="contact" method="post">
		
		  <div class="form-group">
            <sf:label path="contact"> &nbsp;Contact Number </sf:label>
            <sf:input path="contact" class="form-control" name="contact" placeholder="Contact Number" message="Invalid Mobile number" required="true" pattern="[1-9]{1}[0-9]{9}" />
            <div class="form-group">
            <button type="submit" class="btn btn-success btn-lg btn-block">Search</button>	
            <label style="color: red">${message}</label>
        </div>
        </div>
        </sf:form>
        <form>   
        <div class="form-group">
            <label>Question 1</label>
              <input type="text" class="form-control" name="ans3" placeholder="Answer">
        </div>
        <div class="form-group">
             <label path="que2">Question 2</label>
               <input type="text" class="form-control" name="ans3" placeholder="Answer">
        </div>
        <div class="form-group">
               <label>Question 3</label>
            <input type="text" class="form-control" name="ans3" placeholder="Answer">
        </div>
		
	
		<div class="form-group">
            <button type="submit" class="btn btn-success btn-lg btn-block" disabled="disabled">Recover</button>	
        </div>
	<p class="hint-text">Wanna back to login page <a href="login">Click here</a></p>
    </form>
	<div class="text-center">Don't have an account? <a href="user_registration">Register here</a></div>
</div>
</body>
</html>                            