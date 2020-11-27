
<%@page import="com.cognizant.model.Course"%>
<%@page import="com.cognizant.entity.Utility"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>

<%@page import="java.util.List"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Course Reports</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
		<div class="w3-container w3-light-grey w3-padding-32 w3-padding-large"
			id="contact">
		<div class="w3-content" style="max-width: 600px">
				<h4 class="w3-center">
					<b></b>
				</h4>	
			
		<div class="w3-section">
						<f:form class="example" modelAttribute="contact" action="adminReport">
					<f:input path="contact" placeholder="Search..By Email Id" name="search" required="true" pattern="^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$"/>
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
				</f:form>
					</div>
				</div>
		</div>
		<h4 class="w3-padding-16" style="margin-left: 40%">Your Score!</h4>
		<h1>.  Total User   ${totalUser }</h1>
		<c:forEach var="CL" items="${courseList}">
			<div
				class="w3-container w3-dark-grey w3-center w3-text-light-grey w3-padding-32"
				style="margin-left: 4%;margin-right: 4%;margin-bottom: 1px"
				id="about">

				<div class="w3-content w3-justify" style="max-width: 95%">

					<p class="w3-wide">
					<%
					Course course = (Course)pageContext.getAttribute("CL");
					%>
						Course Name :${CL.courseName } 
						| Course ID :	<a href="/admin/adminCourseStudentReport?courseId=${CL.courseId }"><%=Utility.CourseIdGenerator(course.getCourseName(), course.getCourseId()) %></a>
								
						| Enrolled User :  ${ noOfEnrollCourse[CL.courseId]}  <a href="admin_user_feedback?courseId=${CL.courseId }">User's Feedback</a>
						
					</p>
					<div class="w3-white">
						<div class="w3-container w3-padding-small w3-center w3-grey"
							style="width:${ noOfEnrollCourse[CL.courseId]*100/totalUser}%">
						</div>
					</div>
				</div>
			</div>
		</c:forEach>




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