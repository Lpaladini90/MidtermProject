<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="userCreateBetEvent.do" method="POST">
	<div class="form-group">
		<input type="hidden" name="userId" value="${user.id}"> <label
			for="name">Event Name</label> <input id="name" type="text"
			placeholder="Betting Event Name" name="name" class="form-control" />
	</div>

	<div class="form-group">
		<label for="description ">Event Description:</label> <input
			id="description" type="text" placeholder="Event Description"
			name="description" class="form-control" />
	</div>

	<div class="form-group">
		<label for="endDate2">Event Expiration Date</label> <input type="date"
			id="endDate2" name="endDate2" value="2023-01-01" min="2023-01-01"
			max="2030-12-31" class="form-control">
	</div>

	<!--  Contenders -->
	<div class="form-group">
		<input style="display: none" name="active" value="1">
		<div id="contender-info">
			<div id="contender-names">
				<label for="endDate2">Contender</label> <input type="text"
					name="contenderName" class="form-control">
			</div>
			<div id="contender-odds" class="mx-2">
				<label for="endDate2">Odds</label> <input type="number"
					name="contenderOdds" min="0" max="100" step="1" class="form-control">
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<button type="button" class="btn btn-primary w-100"
		onclick="addContender()">add contender</button>
	</div>

	<!-- Category -->
	<div class="form-group">
		<label for="category">Category</label> 
		<input type="text" name="categoryString" class="form-control">
	</div>
	
	<!-- Sub Category -->
	<div class="form-group">
			<div id="sub-cateory-container">
			<label for="category">Subcategory's</label>
			<input type="text" name="cname" class="form-control">
		</div>
	</div>
	
	<div class="form-group">
		<button type="button" class="btn btn-primary w-100"
		onclick="addsubcategory()">add subcategory</button>
	</div>
	
	<div class="form-group">
		<button type="submit" class="btn btn-outline-primary w-100">Submit</button>
	</div>

</form>