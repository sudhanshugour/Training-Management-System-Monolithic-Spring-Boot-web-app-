<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>assessment</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" type="text/css" href="/css/style1.css">
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
<body class="w3-light-grey w3-content" style="max-width:1600px">

  <!-- Sidebar/menu -->
  <nav
		class="w3-sidebar w3-bar-block w3-white w3-animate-left w3-text-grey w3-collapse w3-top w3-center"
		style="z-index: 3; width: 300px; font-weight: bold" id="mySidebar">
		<br>
		<h3 class="w3-padding-64 w3-center">
			<b>${userName}<br>${userId}
			</b>
		</h3>
		
	</nav>

  <!-- Top menu on small screens -->
  <header class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding-16">
    <span class="w3-left w3-padding">USER NAME</span>
    <a href="javascript:void(0)" class="w3-right w3-button w3-white" onclick="w3_open()">☰</a>
  </header>

  <!-- Overlay effect when opening sidebar on small screens -->
  <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
    title="close side menu" id="myOverlay"></div>

  <!-- !PAGE CONTENT! -->
  <div class="w3-main" style="margin-left:300px">




    <div class="w3-black w3-center w3-padding-24">Powered by</div>



    <h4 class="w3-padding-16">Quiz time !</h4>


    <form action="assessmentResult">
        <c:forEach var="QnA" items="${QnAList}">
	    <div class="scp-quizzes-data" style="padding-left:30px">
	 
	      <h3>${QnA.question }</h3>
	      <input type="checkbox"  name="${QnA.questionAndAnswerId}_1">
	      1. ${QnA.option1 }<br>
	      <input type="checkbox"   name="${QnA.questionAndAnswerId}_2">
	      2. ${QnA.option2 }<br>
	      <input type="checkbox"  name="${QnA.questionAndAnswerId}_3">
	      3. ${QnA.option3 }<br>
	      <input type="checkbox" name="${QnA.questionAndAnswerId}_4">
	      4. ${QnA.option4 }<br>
	
	    </div>
     </c:forEach><br><br>
     <input type="hidden" name="skillId" value="${skill.skillId }">
     <input type="hidden" name="userAssessmentId" value="${userAssessmentId }">
					<button type="submit" style="margin-bottom:5px"	class="w3-button w3-block w3-green"	>
					Submit Test
					</button><br><br><br>
    </form>

    

 




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
    
  </script>


</body>

</html>