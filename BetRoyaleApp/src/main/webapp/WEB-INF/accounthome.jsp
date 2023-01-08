<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Account Info</title>
<jsp:include page="bootstraphead.jsp" />
<link rel="stylesheet" href="css/accounthome.css">


</head>
<body>
	<jsp:include page="Components/navbar.jsp" />

	<div id="account-page-container" class="d-flex">

		<!-- Start Test  -->
		<div class="main-body w-100">
			<div class="row gutters-sm">
				<!-- --------  top profile ------- -->
				<div class="col-md-4 mb-3">
					<div class="card">
						<div class="card-body">
							<div class="d-flex flex-column align-items-center text-center">
								<img src="https://bootdey.com/img/Content/avatar/avatar7.png"
									alt="Admin" class="rounded-circle" width="150">
								<div class="mt-3">
									<h4>${user.fullName}</h4>
									<p class="text-secondary mb-1">${user.username }</p>
									<button type="button" class="btn btn-primary"
										onclick="location.href='loadEvents.do?userId=${user.id}';">View
										Events</button>

								</div>
							</div>
						</div>
					</div>


					<div class="card mt-3">
						<div class="list-group" id="myList" role="tablist">
							<a onclick="loadDisplay(this.id)" id="loadAllBets"
								class="list-group-item list-group-item-action-active"
								data-bs-toggle="list" href="allBetEvents.do" role="tab">My
								Profile</a> <a onclick="loadDisplay(this.id)" id="createEvent"
								class="list-group-item list-group-item-action"
								data-bs-toggle="list" href="userCreateBetEvent.do" role="tab">Create
								a Betting Event</a> <a onclick="loadDisplay(this.id)"
								id="leaderboard" class="list-group-item list-group-item-action"
								data-bs-toggle="list" href="getLeaderboard.do" role="tab">View
								Leaderboard</a> <a onclick="loadDisplay(this.id)" id="myWagers"
								class="list-group-item list-group-item-action"
								data-bs-toggle="list" href="getWagers.do" role="tab">View My
								Wagers</a> <a onclick="loadDisplay(this.id)" id="updateUser"
								class="list-group-item list-group-item-action"
								data-bs-toggle="list" href="updateUser.do" role="tab">Edit
								Profile</a>
						</div>
					</div>
				</div>
				<!-- --------- Tab selection section ------ -->
				<div class="col-md-8">
					<div class="card mb-3">
						<!-- <div class="container"> -->
						<div class="card-body" id="View">
							<div class="tab-content">
								<div class="tab-pane active" id="loadAllBetsview"
									role="tabpanel">
									<div class="col-md-12">
										<div class="card mb-3">
											<div class="card-body">
												<div class="row">
													<div class="col-sm-3">
														<h6 class="mb-0">Full Name</h6>
													</div>
													<div class="col-sm-9 text-secondary">
														${user.fullName}</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-sm-3">
														<h6 class="mb-0">Email</h6>
													</div>
													<div class="col-sm-9 text-secondary">${user.email}</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-sm-3">
														<h6 class="mb-0">Username</h6>
													</div>
													<div class="col-sm-9 text-secondary">${user.username }</div>
												</div>
												<hr>
												<div class="row">
													<div class="col-sm-3">
														<h6 class="mb-0">About Me</h6>
													</div>
													<div class="col-sm-9 text-secondary">${user.aboutMe}</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="createEventview" role="tabpanel">
									<jsp:include page="Components/createBettableEventForm.jsp" />
								</div>
								<!-- ----------------- Leader Board--------------------- -->
								<div class="tab-pane" id="leaderboardview" role="tabpanel">
									<table class="table table-hover text-nowrap">
										<thead>
											<tr>
												<th scope="col">Rank</th>
												<th scope="col">Username</th>
												<th scope="col">Count</th>
												<th scope="col">Winnings</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${rankings}" var="entry">
												<tr>
													<th scope="row">${entry.rank}</th>
													<td><span class="text-success"> <i
															class="fas fa-caret-down me-1"></i> <span>${entry.user.username}
														</span>
													</span></td>
													<td><span class="text-success"> <i
															class="fas fa-caret-up me-1"></i> <span>${entry.count}</span>
													</span></td>
													<td><span class="text-success"> <i
															class="fas fa-caret-up me-1"></i> <span>${entry.total}</span>
													</span></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- ------------------- Wagers Tab ----------------------->
								<div class="tab-pane" id="myWagersview" role="tabpanel">
									<div class="tab-pane" id="tabss" role="tabpanel">
										<nav>
											<div class="nav nav-tabs" id="nav-tab" role="tablist">
												<button class="nav-link active" id="nav-home-tab"
													data-bs-toggle="tab" data-bs-target="#nav-home"
													type="button" role="tab" aria-controls="nav-home"
													aria-selected="true">Expired</button>
												<button class="nav-link" id="nav-profile-tab"
													data-bs-toggle="tab" data-bs-target="#nav-profile"
													type="button" role="tab" aria-controls="nav-profile"
													aria-selected="false">All</button>
											</div>
										</nav>
										<div class="tab-content" id="nav-tabContent">
											<div class="tab-pane fade show active" id="nav-home"
												role="tabpanel" aria-labelledby="nav-home-tab">
												<!-- ---------------------- pane 1 -->
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
															<c:if test="${wager.contender.event.completion}">
																<tr>
																	<th scope="row">${wager.user.username}</th>
																	<td><span class="text-success"> <i
																			class="fas fa-caret-down me-1"></i> <span>${wager.betAmount}</span>
																	</span></td>
																	<td><span class="text-success"> <i
																			class="fas fa-caret-up me-1"></i> <span>${wager.contender.name}</span>
																	</span></td>
																	<td><c:if
																			test="${wager.contender.event.completion}">
																			<span class="text-success"> <i
																				class="fas fa-caret-up me-1"></i> <span>${wager.betAmount * wager.multiplier}</span>
																			</span>
																		</c:if></td>
																</tr>
															</c:if>
														</c:forEach>
													</tbody>
												</table>
												<!-- ---------------------- end pane 1 -->
											</div>
											<div class="tab-pane fade" id="nav-profile" role="tabpanel"
												aria-labelledby="nav-profile-tab">
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
																<td><span class="text-success"> <i
																		class="fas fa-caret-down me-1"></i> <span>${wager.betAmount}</span>
																</span></td>
																<td><span class="text-success"> <i
																		class="fas fa-caret-up me-1"></i> <span>${wager.contender.name}</span>
																</span></td>
																<td><c:if
																		test="${wager.contender.event.completion}">
																		<span class="text"> <i
																			class="fas fa-caret-up me-1"></i> <span>${wager.betAmount*wager.multiplier}</span>
																		</span>
																	</c:if></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="updateUserview" role="tabpanel">
									<jsp:include page="Components/updateuser.jsp" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--  End TAb Section-->
			</div>
		</div>
	</div>

	<jsp:include page="Components/footer.jsp" />
	<jsp:include page="bootstrapfoot.jsp" />


</body>
</html>