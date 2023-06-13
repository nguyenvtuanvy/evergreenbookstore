<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Customer Profile" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">&nbsp;</div>
		
		<div class="row">
			<div class="col text-center">
				<h3>Welcome, ${loggedcustomer.fullname}</h3>
			</div>
		</div>
		
		<div class="row">&nbsp;</div>
		
		<div class="row" style="max-width: 305px; margin: 0 auto;">
			<div class="col text-center">
				<div class="row">
					<label class="col-sm"><b>Email Address:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.email}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>First Name:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.firstname}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>Last Name:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.lastname}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>Phone Number:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.phoneNumber}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>Address Line 1:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.addressLine1}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>Address Line 2:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.addressLine1}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>City:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.city}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>Zip Code:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.zipCode}</div>
				</div>
				<div class="row">
					<label class="col-sm"><b>Country:</b></label>
					<div class="col-sm" align="left">${loggedcustomer.countryName}</div>
				</div>
			<div class="row">&nbsp;</div>

			<div class="row">
				<div class="col text-center">
					<b><a href="edit_profile">Edit My Profile </a></b>
				</div>
			</div>
			</div>
		</div>

		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>