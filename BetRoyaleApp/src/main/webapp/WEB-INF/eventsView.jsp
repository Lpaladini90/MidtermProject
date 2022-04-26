<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Events View</title>
<link rel="stylesheet" href="static/css/reset.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<div class="main-container">
		<!--------------------------------- Card ------------------------------->
		<c:forEach items="${events}" var="e">
			<div class="event-card">
				<div class="event-image-container">
					<img class="col bet-event-img"
						src="https://avatars.githubusercontent.com/u/34669927?s=40&v=4"
						width="75px" height="75px"></img>
				</div>
				<div class="event-info-container">
					<div class="category-container">
						<div class="tag category">sports</div>
						<div class="tag sub-category">football</div>
						<div class="tag sub-category">esports</div>
					</div>
					<div>
						<h3>${e.name}</h3>
						<h5>close date: ${e.endDate}</h5>
					</div>
				</div>
			</div>

		</c:forEach>

		<!--------------------------------- Card ------------------------------->
		<div class="event-card">
			<div class="event-image-container">
				<img class="col bet-event-img"
					src="https://avatars.githubusercontent.com/u/34669927?s=40&v=4"
					width="75px" height="75px"></img>
			</div>
			<div class="event-info-container">
				<div class="category-container">
					<div class="tag category">sports</div>
					<div class="tag sub-category">football</div>
					<div class="tag sub-category">esports</div>
				</div>
				<div>
					<h3>This is Okay</h3>
				</div>
			</div>
		</div>
		<!--------------------------------- Card ------------------------------->
	</div>

</body>
</html>