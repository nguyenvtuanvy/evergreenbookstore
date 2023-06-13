<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="page_head.jsp">
	<jsp:param value="Edit Profile" name="pageTitle" />
</jsp:include>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">&nbsp;</div>

		<div class="row">
			<div class="col text-center">
				<h2>Edit My Profile</h2>
			</div>
		</div>

			<form action="update_profile" method="post" style="max-width: 800px; margin: 0 auto;">
					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">E-mail :</label>
						<div class="col-sm-8"><b>${loggedcustomer.email}</b>(Cannot be
							changed)</div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">First Name :</label>
						<div class="col-sm-8"><input type="text" id="firstname" class="form-control"
							name="firstname" value="${loggedcustomer.firstname}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Last Name :</label>
						<div class="col-sm-8"><input type="text" id="lastname" class="form-control"
							name="lastname" value="${loggedcustomer.lastname}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Phone Number :</label>
						<div class="col-sm-8"><input type="text" id="phoneNumber"
							class="form-control" name="phoneNumber"
							value="${loggedcustomer.phoneNumber}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Address Line 1:</label>
						<div class="col-sm-8"><input type="text" id="address1" class="form-control"
							name="address1" value="${loggedcustomer.addressLine1}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Address Line 2:</label>
						<div class="col-sm-8"><input type="text" id="address2" class="form-control"
							name="address2" value="${loggedcustomer.addressLine2}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">City :</label>
						<div class="col-sm-8"><input type="text" id="city" class="form-control"
							name="city" value="${loggedcustomer.city}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">State :</label>
						<div class="col-sm-8"><input type="text" id="state" class="form-control"
							name="state" value="${loggedcustomer.state}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Zip Code :</label>
						<div class="col-sm-8"><input type="text" id="zipCode" class="form-control"
							name="zipCode" value="${loggedcustomer.zipCode}" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Country :</label>
						<div class="col-sm-8"><select name="country" class="form-control">
								<c:forEach items="${mapCountries}" var="country">
									<option value="${country.value}"
										<c:if test='${loggedcustomer.country eq country.value}'>selected='selected'</c:if>>${country.key}</option>
								</c:forEach>
						</select></div>
					</div>
					<div class="form-group row">
						<div><i>(leave password filed
								blank if you don't want to change password)</i></div>
					</div>
					
					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Password :</label>
						<div class="col-sm-8"><input type="password" id="password"
							class="form-control" name="password" /></div>
					</div>

					<div class="form-group row">
						<label class="col-sm-4 col-form-lable">Confirm Password :</label>
						<div class="col-sm-8"><input type="password" id="confirmpassword"
							class="form-control" name="confirmpassword" /></div>
					</div>

					<div class="row">
						<div class="col text-center">
							<button type="submit" class="btn btn-primary mr-3">Save</button> &nbsp;&nbsp;
							<button type="button" class="btn btn-secondary" onclick="history.go(-1);">Cancel</button>
						</div>
					</div>
			</form>
			
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>