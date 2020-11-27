<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>add question</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<a href="http://localhost:9090/admin/courseTakenReoprt" onclick="w3_close()" class="w3-bar-item w3-button">Course Reoprt</a>
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
				<p>Add questions here !</p>


				<f:form action="addQuestionAndAnswer" modelAttribute="queAndAns" method="Get">

					<div class="w3-section">
						<label>Question</label><br>
						<f:textarea path="question" rows="4" cols="72" required="true" message="Add Question"/> Add some good question here !!!!
						
						<div class="w3-section">
							<label>Option 1</label> 
							<f:input class="w3-input w3-border" path="option1" required="true"/> 
							<f:checkbox  path="option1IsAnswer"/> 
							is Answer
						</div>
												<div class="w3-section">
							<label>Option 2</label> 
							<f:input class="w3-input w3-border" path="option2" required="true"/> 
							<f:checkbox  path="option2IsAnswer"/> 
							is Answer
						</div>
						<div class="w3-section">
							<label>Option 3</label> 
							<f:input class="w3-input w3-border" path="option3" required="true"/> 
							<f:checkbox  path="option3IsAnswer"/> 
							is Answer
						</div>
						<div class="w3-section">
							<label>Option 4</label> 
							<f:input class="w3-input w3-border" path="option4" required="true"/> 
							<f:checkbox  path="option4IsAnswer"/> 
							is Answer
						</div>			
						
					</div>
					<f:hidden path="skillId" value="${skillId}"/>
					<button type="submit" class="w3-button w3-block w3-black w3-margin-bottom">Add</button>
					<label style="color:red;">${mssg}</label>
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