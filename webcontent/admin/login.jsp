<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Admin Login" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h1>Book store Administration</h1>
			</div>
		</div>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Admin Login</h2>
			</div>
		</div>
		
		<div class="row">&nbsp;</div>
		<c:if test="${message != null}">
			<div class="row">
				<div class="col text-center" style="color: red;">
					<h4>${message}</h4>
				</div>
			</div>
		</c:if>
		<div class="row">&nbsp;</div>
		<form action="login" method="post"
			style="max-width: 400px; margin: 0 auto;">
			<div class="border border-secondary rounded p-3">
				<div class="form-group row">
					<label class="col-sm-4 col-form-lable">Email:</label>
					<div class="col-sm-8">
						<input type="email" name="email" id="email" size="20" required
							minlength="10" maxlength="30" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-4 col-form-lable">Password:</label>
					<div class="col-sm-8">
						<input type="password" name="password" id="password" size="20"
							required minlength="4" maxlength="32" />
					</div>
				</div>
				<div class="row">
					<div class="col text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>