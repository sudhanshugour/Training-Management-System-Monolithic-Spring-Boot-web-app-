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
<script>
function onClick(element) {
    document.getElementById("img01").src = element.src;
    document.getElementById("modal01").style.display = "block";
    var captionText = document.getElementById("caption");
    captionText.innerHTML = element.alt;
  }
  history.pushState(null, document.title, location.href);
  window.addEventListener('popstate', function (event)
  {
    history.pushState(null, document.title, location.href);
  });
  history.pushState(null, document.title, location.href);
  window.addEventListener('popstate', function (event)
  {
    history.pushState(null, document.title, location.href);
  });
</script>
</head>
<body>

<div class="signup-form container">
    <sf:form action="loginForm" modelAttribute="login"  method="post">
		<h2>Login</h2>
		<p class="hint-text">Bonjour, Fill your Credentials here !</p>
   
        <div class="form-group">
        	<sf:input path="userId" class="form-control" name="UserId" placeholder="User ID" required="true" pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"/>
             <div><sf:errors path="userId"></sf:errors> </div>       
        </div>
		<div class="form-group">
            <sf:password  path="password" class="form-control" name="password" placeholder="Password" required="true"/>
            <div><sf:errors path="password"></sf:errors></div>
        </div>
	
		<div class="form-group">
            <button type="submit" class="btn btn-success btn-lg btn-block">Login</button>
            	
        </div>
        <label style="color:red">${para}</label>
	<p class="hint-text">Need Help ! <a href="recover_user">Forgot User Id</a> Or <a href="recover_password">Forgot Password</a>  </p>
	<div class="text-center">Don't have an account? <a href="user_registration">Register here</a></div>
    </sf:form>
	
	
</div>
</body>
</html>                            