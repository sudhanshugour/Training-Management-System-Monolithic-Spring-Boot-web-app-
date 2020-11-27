<%@ page import="com.cognizant.model.Course" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.cognizant.entity.Utility" language="java"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>course_details</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
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
		<a href="http://localhost:9090/user/user_search" onclick="w3_close()" class="w3-bar-item w3-button">Search Course</a>
		<a href="http://localhost:9090/user/user_enrolled_courses" onclick="w3_close()" class="w3-bar-item w3-button">My Courses</a>
		<a href="http://localhost:9090/user/userAssessmentsList?" onclick="w3_close()" class="w3-bar-item w3-button">Assessment Board</a>
		<a href="http://localhost:9090/user/logOut" onclick="w3_close()" class="w3-bar-item w3-button">Log Out</a>
	</nav>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding-16">
		<span class="w3-left w3-padding">User NAME</span> <a
			href="javascript:void(0)" class="w3-right w3-button w3-white"
			onclick="w3_open()">☰</a>
	</header>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px">

		<div class="w3-black w3-center w3-padding-24">Powered by</div>


		<h3 class="w3-padding-16">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>About the course</b>
		</h3>



		<f:form action="updateform" modelAttribute="course">
			<table style="padding-left: 5%;">
				<tr>
					<td>
						<h4 class="w3-padding-16">
							<b>Course name</b>
						</h4>
					</td>
					<td style="padding-left: 5%;">${xyz.courseName}</td>
				</tr>
				<tr>
					<td>
						<h4 class="w3-padding-16">
							<b>Course id</b>
						</h4>
					</td>
					<%Course course = (Course)request.getAttribute("xyz");%>
					<td style="padding-left: 5%;"><%=Utility.CourseIdGenerator(course.getCourseName(), course.getCourseId()) %></td>
				</tr>
				<tr>
					<td>
						<h4 class="w3-padding-16">
							<b>Course details</b>
						</h4>
					</td>
					<td style="padding-left: 5%;">${xyz.courseDetail}</td>
				</tr>
				<tr>
					<td>
						<h4 class="w3-padding-16">
							<b>Competency level</b>
						</h4>
					</td>
					<td style="padding-left: 5%;">${xyz.competencyLevel}</td>
				</tr>
				<tr>
					<td>
						<h4 class="w3-padding-16">
							<b>Intended audience</b>
						</h4>
					</td>
					<td style="padding-left: 5%;">${xyz.intendedAudience}</td>
				</tr>
				<tr>
					<td>
						<h4 class="w3-padding-16">
							<b>Prerequisites</b>
						</h4>
					</td>
					<td style="padding-left: 5%;">${xyz.prerequisites}</td>
				</tr>

				<tr>
					<td>
						<h4 class="w3-padding-16">
							<b>Duration</b>
						</h4>
					</td>
					<td style="padding-left: 5%;">${xyz.duration}Weeks</td>
				</tr>
				<f:hidden path="courseId" name="courseId" value="${xyz.courseId}" />
			</table>
		</f:form>
		<br> 
		<br>
	
		<%-- <form action="Enroll" style="padding-left: 5%;">--%>
			
		
    
     
			<%--</form>--%>
		

		<c:forEach var="skill" items="${skillList}">
			<div class="w3-half" style="padding: 25px 25px 25px 25px;">
				<ul class="w3-ul w3-white w3-center w3-opacity w3-hover-opacity-off">
					<li class="w3-black w3-xlarge w3-padding-32">${skill.skillName}</li>
					<li class="w3-light-grey w3-padding-24">
						
					</li>
				</ul>
			</div>
		</c:forEach>


	
	 <a href="enroll?courseId=${xyz.courseId}">
							<button type="submit" class="w3-button w3-white w3-padding-large" style="margin:auto;display:block;">Enroll</button>
							
					</a>
		



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