<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Add Admin</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/style1.css">
</head>	


<body class="w3-light-grey w3-content" style="max-width: 1600px">

	<!-- Sidebar/menu -->
				<nav
		class="w3-sidebar w3-bar-block w3-white w3-animate-left w3-text-grey w3-collapse w3-top w3-center"
		style="z-index: 3; width: 300px; font-weight: bold" id="mySidebar">
		<br>
		<h3 class="w3-padding-64 w3-center">
			<b>${userName}<br>${userId}
			</b>
		</h3>
		<!--<a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-padding w3-hide-large">CLOSE</a> -->
		<a href="http://localhost:9090/admin/viewcourse" onclick="w3_close()" class="w3-bar-item w3-button">My Courses</a>
		<a href="http://localhost:9090/admin/addCourseform" onclick="w3_close()" class="w3-bar-item w3-button">Create Course</a>
		<a href="http://localhost:9090/admin/admin_registration" onclick="w3_close()" class="w3-bar-item w3-button">Add Admin</a>
		<a href="http://localhost:9090/admin/courseTakenReoprt" onclick="w3_close()" class="w3-bar-item w3-button">Course Report</a>
		<a href="http://localhost:9090/admin/logOut" onclick="w3_close()" class="w3-bar-item w3-button">Log Out</a>
	</nav>
	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding-16">
	<span class="w3-left w3-padding">USER NAME</span> <a
		href="javascript:void(0)" class="w3-right w3-button w3-white"
		onclick="w3_open()">â˜°</a> </header>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px">


		<div class="w3-black w3-center w3-padding-24">Powered by</div>

		<div class="signup-form container" style="width: 40%;margin: 24%;">
		<sf:form action="adminRegistration" modelAttribute="person" method="post">
			<h2>Register</h2>
			<p class="hint-text">Add New Admin. Make New Creators.</p>
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
				<sf:hidden  class="form-control" name="role" path="role" value="admin"/>
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
				<button type="submit" class="btn btn-success btn-lg btn-block">Add Admin</button>
				<label style="color:red">${para}</label>
				<label style="color:green">${paraa}</label>
			</div>
		</sf:form>





		<br> <br> <br>

		<!-- End page content -->
	</div>

	<script>
		// Script to open and close sidebar
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
			document.getElementById("myOverlay").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
			document.getElementById("myOverlay").style.display = "none";
		}

		// Modal Image Gallery
		function onClick(element) {
			document.getElementById("img01").src = element.src;
			document.getElementById("modal01").style.display = "block";
			var captionText = document.getElementById("caption");
			captionText.innerHTML = element.alt;
		}
	</script>


</body>

</html>