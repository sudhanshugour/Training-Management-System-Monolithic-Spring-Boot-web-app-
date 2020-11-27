<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>update course</title>
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
		<a href="http://localhost:9090/admin/viewcourse" onclick="w3_close()" class="w3-bar-item w3-button">My Courses</a>
		<a href="http://localhost:9090/admin/addCourseform" onclick="w3_close()" class="w3-bar-item w3-button">Create Course</a>
		<a href="http://localhost:9090/admin/admin_registration" onclick="w3_close()" class="w3-bar-item w3-button">Add Admin</a>
		<a href="http://localhost:9090/admin/courseTakenReoprt" onclick="w3_close()" class="w3-bar-item w3-button">Course Reoprts</a>
		<a href="http://localhost:9090/admin/logOut" onclick="w3_close()" class="w3-bar-item w3-button">Log Out</a>
	</nav>
	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding-16">
		<span class="w3-left w3-padding">Admin NAME</span> <a
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


		<div class="w3-container w3-light-grey w3-padding-32 w3-padding-large"
			id="contact">
			<div class="w3-content" style="max-width: 600px">
				<h4 class="w3-center">
					<b></b>
				</h4>
				<p>Update all the course details here !</p>


				<f:form action="update" modelAttribute="course">
					<f:hidden path="courseId" />
					<f:hidden path="userId" value="${xyz.userId}" required="true" /><%--replace with session --%>
					<div class="w3-section">
						<label>Course Name</label>
						<f:input class="w3-input w3-border" path="courseName"
							value="${xyz.courseName}" required="true" />
					</div>
					<span style="color: red"><f:errors path="courseName"></f:errors></span>

					<div class="w3-section">
						<label>Course details</label><br>
						<f:textarea name="courseDetail" rows="4" cols="72"
							path="courseDetail" value="${xyz.courseDetail}" required="true" />
					</div>
					<span style="color: red"><f:errors path="courseDetail"></f:errors></span>

					<div class="w3-section">
						<label> Competency level</label><br> <br>
						<f:select path="competencyLevel" id="competencyLevel"
							items="${competencyLevelList}" />
						"${xyz.competencyLevel}"
					</div>
					<span style="color: red"><f:errors path="competencyLevel"></f:errors></span>

					<div class="w3-section">
						<label>Intended audience</label>
						<f:input class="w3-input w3-border" path="intendedAudience"
							value="${xyz.intendedAudience}" required="true"/>
					</div>
					<span style="color: red"><f:errors path="intendedAudience"></f:errors></span>
					
					<div class="w3-section">
						<label>Feedback Question</label>
						<f:input class="w3-input w3-border" path="feedbackQue" value="${xyz.feedbackQue}" required="true" />
					</div>
					<span style="color: red"><f:errors path="feedbackQue"></f:errors></span>
					
					<div class="w3-section">
						<label>Prerequisites</label>
						<f:input class="w3-input w3-border" path="prerequisites"
							value="${xyz.prerequisites}" required="true" />
					</div>
					<span style="color: red"><f:errors path="prerequisites"></f:errors></span>

					<div class="w3-section">
						<label>Duration (in weeks)</label>
						<f:input class="w3-input w3-border" path="duration"
							value="${xyz.duration}" required="true" pattern="[1-9]{1}[0-9]{0,1}"/>
					</div>
					<span style="color: red"><f:errors path="duration"></f:errors></span>

					<button type="submit"
						class="w3-button w3-block w3-black w3-margin-bottom">confirm
						changes</button>
				</f:form>



				<f:form action="updateSkill">
					<div class="w3-section">
						<label>Skills (please sparate multiple skills with ' <b>;</b>
							' )
						</label> <input class="w3-input w3-border" placeholder="${skills}" type="text" name="skills"/>
						<input class="w3-input w3-border" type="hidden" name="courseIdResponse" value="${xyz.courseId}" />
					</div>
					<button type="submit"
						class="w3-button w3-block w3-black w3-margin-bottom">Add
						Skills</button>
				</f:form>

			</div>
		</div>





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