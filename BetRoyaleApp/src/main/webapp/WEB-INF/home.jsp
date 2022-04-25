<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bet Royale App</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
<jsp:include page="Components/navbar.jsp" />
<jsp:include page="Components/createuser.jsp" />

<br>
------------------------------------------------- user login --------------


<jsp:include page="Components/userlogin.jsp" />
	

<h1>Betting Royale </h1>


--------------------------------------------------------------------------






--------------------------------------------------------------------------
<!-- Search testing -->
<br>
${username.username} }

-----------------------Create Wager-----------------------------------------
<br>






-------------------------------Display all bettable events---------------------------
	
	

<form action="allBetEvents" method="GET">

<input type="submit" id="betEvent" name="betEvent" value="DisplayAllBetEvents"/>

</form>
	
	<ol>
	<c:forEach var="e" items="${betEvent}">
					<li>${e.name} </li>
					<li>${e.description} </li>
					<li>${e.endDate} </li>
			----		---------------------------
				</c:forEach>
	</ol>
	
-------------------------------Display all active bettable events---------------------------
	
	<ol>
	<c:forEach var="e" items="${activeBetEvent}">
					<li>${e.name} </li>
					<li>${e.description} </li>
					<li>${e.endDate} </li>
					-------------------------------
				</c:forEach>
	</ol>
	
	
	
-------------------------------Display all expired bettable events---------------------------
	
	
	
	<ol>
	<c:forEach var="e" items="${expiredBetEvent}">
					<li>${e.name} </li>
					<li>${e.description} </li>
					<li>${e.endDate} </li>
					-------------------------------
				</c:forEach>
	</ol>





------------------------------- Add Comment ---------------------------


<jsp:include page="Components/addCommentForm.jsp" />
-------------------------------Display all user created bettable events---------------------------
	
	
	
<jsp:include page="Components/usercreatedbetsview.jsp" />
	
	
	






</body>
</html>