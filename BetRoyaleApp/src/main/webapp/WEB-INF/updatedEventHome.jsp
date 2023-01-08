<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html
	class="js sizes customelements history pointerevents postmessage webgl websockets cssanimations csscolumns csscolumns-width csscolumns-span csscolumns-fill csscolumns-gap csscolumns-rule csscolumns-rulecolor csscolumns-rulestyle csscolumns-rulewidth csscolumns-breakbefore csscolumns-breakafter csscolumns-breakinside flexbox picture srcset webworkers"
	lang="zxx">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Event Info</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- <link rel="manifest" href="site.webmanifest"> -->
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.png">
<!-- Place favicon.ico in the root directory -->

<!-- CSS here -->
<link rel="stylesheet" href="css2/bootstrap.min.css">
<link rel="stylesheet" href="css2/owl.carousel.min.css">
<link rel="stylesheet" href="css2/magnific-popup.css">
<link rel="stylesheet" href="css2/font-awesome.min.css">
<link rel="stylesheet" href="css2/themify-icons.css">
<link rel="stylesheet" href="css2/nice-select.css">
<link rel="stylesheet" href="css2/flaticon.css">
<link rel="stylesheet" href="css2/animate.css">
<link rel="stylesheet" href="css2/slicknav.css">
<link rel="stylesheet" href="css2/style.css">
<!-- <link rel="stylesheet" href="css/responsive.css"> -->
</head>

<body>
	<!--[if lte IE 9]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
        <![endif]-->
	<jsp:include page="Components/navbar.jsp" />
	<!-- slider_area_start -->
	<div class="slider_area slider_bg_1">
		<div class="slider_text">
			<div class="container">
				<div class="position_relv">
					<h1 class="opcity_text d-none d-lg-block">${event.name}</h1>

					<div class="row">
						<div class="col-xl-9">
							<div class="title_text">
								<h3>${event.name}</h3>

								<c:forEach items="${event.contenders}" var="contender">

									<div class="col-xl-4 col-md-6 col-lg-4">
										<div class="single_date">
											<span>${contender.name}</span>
										</div>
									</div>
								</c:forEach>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="countDOwn_area">
			<div class="container">
				<div class="row align-items-center">
					<div class="col-xl-4 col-md-6 col-lg-4">
						<div class="single_date">
							<span>Close date<br> ${event.endDate}
							</span>
						</div>
					</div>
					<div class="col-xl-3 col-md-6 col-lg-3">
						<div class="single_date">
							<!-- <i class="ti-alarm-clock"></i> -->
							<span></span>
						</div>
					</div>

					<div class="col-xl-5 col-md-12 col-lg-5">
						<div id="countdown" class="countdown_time single_countdown"></div>
						<span id="clock"><div class="countdown_time">
								<div class="single_countdown">
									<h3 id="daysLeft">00</h3>
									<span>days</span>
								</div>
								<div class="single_countdown">
									<h3 id="hoursLeft">00</h3>
									<span>Hours</span>
								</div>
								<div class="single_countdown">
									<h3 id="minutesLeft">00</h3>
									<span>Minutes</span>
								</div>
								<div class="single_countdown">
									<h3 id="secondsLeft">00</h3>
									<span>Seconds</span>
								</div>
							</div></span>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- slider_area_end -->


	<!-- edit event if user -->
	<div class="event_area">
		<div class="container">
			<!-- --- gives the user the ability to edit event if event belongs to user -->
			<c:if test="${event.user.id == user.id}">
				<c:if test="${not event.completion}">
					<button type="button" class="btn btn-warning" id="edit"
						onclick="toggleEventForm()">Edit Event</button>
					<div class="hidden" id="editEventForm">
						<jsp:include page="Components/editbettingevent.jsp" />
					</div>
				</c:if>
			</c:if>
		</div>
	</div>


	<!-- event_area_start -->

	<div class="event_area">
		<div class="container">
			<div class="serction_title_large mb-95">
				<h3>Place Wagers</h3>

			</div>
			<!-- Allow user to place wager if event is still active -->
			<c:if test="${event.active}">
				<div class="place-wager-container">
					<div id="contenders">
						<c:forEach items="${event.contenders}" var="contender">
							<button type="button" class="btn btn-secondary"
								id="${contender.id}" onclick="toggleContender(this.id)">${contender.name}(${contender.odds})</button>
						</c:forEach>
					</div>
					<div>
						<form action="createWager.do" method="POST" id="create-wager-form">
							<input type="hidden" id="userId" name="userId" value="${user.id}" />
							<label for="userId"></label> <input type="hidden"
								id="contenderName" name="contendername"
								placeholder="Enter Contender Id" /> <input type="text"
								id="contenderId" name="contenderId" required hidden /> <label
								for="betAmount">Bet Amount: </label> <input id="betAmount"
								type="text" placeholder="Bet Amount" name="betAmount" /> <input
								type="submit" />
						</form>
					</div>

				</div>
			</c:if>


		</div>
	</div>

	<div class="event_area">
		<div class="container">
			<div class="serction_title_large mb-95">
				<h3>Event Wagers</h3>
			</div>
			<div class="table-switch">
				<button type="button" class="btn btn-primary" id="all"
					onclick="toggleWagerTable('all-wagers-table')">All Wagers</button>
				<button type="button" class="btn btn-secondary" id="user"
					onclick="toggleWagerTable('user-wagers-table')">User
					Wagers</button>
			</div>
			<div id="all-wagers-table" class="wager-table-container">


				<h3>All Wagers</h3>
				<table class="table table-hover text-nowrap">
					<thead>
						<tr>
							<th scope="col">Username</th>
							<th scope="col">Bet Amount</th>
							<th scope="col">Contender</th>
							<th scope="col">Payout</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${wagers}" var="wager">
							<tr>
								<th scope="row">${wager.user.username}</th>
								<td><span class="text-success"> <span>${wager.betAmount}</span>
								</span></td>
								<td><span class="text-danger"> <span>${wager.contender.name}</span>
								</span></td>
								<td><c:if test="${event.completion}">
										<span class="text-success"> <span>${wager.betAmount * wager.multiplier}</span>
										</span>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


			<div id="user-wagers-table"
				class="wager-table-container user-wagers hidden">
				<h3>User Wagers</h3>
				<table class="table table-hover text-nowrap">
					<thead>
						<tr>
							<th scope="col">Username</th>
							<th scope="col">Bet Amount</th>
							<th scope="col">Contender</th>
							<th scope="col">Payout</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userWagers}" var="wager">
							<tr>
								<th scope="row">${wager.user.username}</th>
								<td><span class="text-danger"> <span>${wager.betAmount}</span>
								</span></td>
								<td><span class="text-success"> <span>${wager.contender.name}</span>
								</span></td>
								<td><c:if test="${event.completion}">
										<span class="text-success"> <span>${wager.betAmount * wager.multiplier}</span>
										</span>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>


	</div>
	<!-- event_area_end -->


	<!-- faq_area_Start -->
	<div class="faq_area">
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="serction_title_large mb-95">
						<h3>Event Comments</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-12">
					<div id="accordion">

						<c:forEach items="${comments}" var="comment">
							<div class="card">
								<div class="card-header" id="headingOne">
									<h5 class="mb-0"
										<span class="time-left">@${comment.user.username}</span>>
										<p>${comment.commentText}</p>

									</h5>
								</div>
							</div>
						</c:forEach>
						<div class="card">
							<div class="card-header" id="headingOne">
								<h5 class="mb-0">
									<form action="addComment.do">
										<div class="form-group">
											<input type="hidden" name="userId" value="${user.id}" /> <input
												type="hidden" name="eventId" value="${event.id}" /> <input
												type="text" name="commentText" class="form-control"
												id="formGroupExampleInput" placeholder="comment" required>
											<input type="submit" name="add Comment" />
										</div>
									</form>
								</h5>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- End -->
		</div>

	</div>
	<!-- faq_area_end -->


<jsp:include page="Components/footer.jsp" />
	<jsp:include page="bootstrapfoot.jsp" />

	<!-- JS here -->
	<script>
		function toggleEventForm() {
			document.getElementById("editEventForm").classList.toggle("hidden")
		}

		function toggleContender(clicked_id) {
			let container = document.getElementById("contenders");
			let children = container.children;
			for (var i = 0; i < children.length; ++i) {
				var child = children[i];
				var contenderId = child.id;
				if (contenderId == clicked_id) {
					if (child.classList.contains("btn-secondary")) {
						child.classList.add("btn-primary");
						child.classList.remove("btn-secondary")
						document.getElementById("contenderId").value = clicked_id;
						console.log(document.getElementById("contenderId"));
					}
				} else {
					child.classList.remove("btn-primary")
					child.classList.add("btn-secondary")
				}
			}
		}

		function toggleWagerTable(selectedTable) {
			console.log(selectedTable)
			if (selectedTable == "all-wagers-table") {
				document.getElementById("all-wagers-table").classList
						.remove("hidden")
				document.getElementById("user-wagers-table").classList
						.add("hidden")
				document.getElementById("all").classList
						.remove("btn-secondary")
				document.getElementById("all").classList.add("btn-primary")
				document.getElementById("user").classList.remove("btn-primary")
				document.getElementById("user").classList.add("btn-secondary")

			} else {//user-wagers-table
				document.getElementById("all-wagers-table").classList
						.add("hidden")
				document.getElementById("user-wagers-table").classList
						.remove("hidden")
				document.getElementById("user").classList
						.remove("btn-secondary")
				document.getElementById("user").classList.add("btn-primary")
				document.getElementById("all").classList.remove("btn-primary")
				document.getElementById("all").classList.add("btn-secondary")
			}
		}
	</script>
	<script>
		//var end = new Date('04/30/2022 10:1 AM');
		var endStr = "${event.endDate}";
		console.log(endStr);
		var end = new Date("${event.endDate}");

		var _second = 1000;
		var _minute = _second * 60;
		var _hour = _minute * 60;
		var _day = _hour * 24;
		var timer;

		function showRemaining() {
			var now = new Date();
			var distance = end - now;
			if (distance < 0) {

				clearInterval(timer);
				document.getElementById('countdown').innerHTML = 'EXPIRED!';

				return;
			}
			var days = Math.floor(distance / _day);
			var hours = Math.floor((distance % _day) / _hour);
			var minutes = Math.floor((distance % _hour) / _minute);
			var seconds = Math.floor((distance % _minute) / _second);

			document.getElementById('daysLeft').innerHTML = days;
			document.getElementById('hoursLeft').innerHTML = hours;
			document.getElementById('minutesLeft').innerHTML = minutes;
			document.getElementById('secondsLeft').innerHTML = seconds;
		}

		timer = setInterval(showRemaining, 1000);
	</script>

	<script src="js/vendor/modernizr-3.5.0.min.js"></script>
	<script src="js/vendor/jquery-1.12.4.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/ajax-form.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/jquery.counterup.min.js"></script>
	<script src="js/imagesloaded.pkgd.min.js"></script>
	<script src="js/scrollIt.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/nice-select.min.js"></script>
	<script src="js/jquery.slicknav.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/jquery.countdown.js"></script>
	<script src="js/plugins.js"></script>

	<!--contact js-->
	<script src="js/contact.js"></script>
	<script src="js/jquery.ajaxchimp.min.js"></script>
	<script src="js/jquery.form.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/mail-script.js"></script>

	<script src="js/main.js"></script>



	<a id="scrollUp" href="#top"
		style="position: fixed; z-index: 2147483647; display: block;"><i
		class="fa fa-angle-double-up"></i></a>
</body>
</html>