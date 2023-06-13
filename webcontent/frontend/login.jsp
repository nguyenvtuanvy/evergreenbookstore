<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Customer Login" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Customer Login</h2>
			</div>
		</div>

		<div class="row">&nbsp;</div>

		<c:if test="${message != null}">
			<div class="row">
				<div class="col text-center">
					<h4 class="message">${message}</h4>
				</div>
			</div>
		</c:if>

		<form action="login" method="post" style="max-width: 400px; margin: 0 auto;">
			<div class="border border-secondary rounded p-3">
				<div class="form-group row">
					<label class="col-sm-4 col-form-lable">Email:</label>
					<div class="col-sm-8">
						<input type="email" name="email" class="form-control" required
							minlength="5" maxlength="64" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-4 col-form-lable">Password:</label>
					<div class="col-sm-8">
						<input type="password" name="password" class="form-control"
							required minlength="4" maxlength="16" />
					</div>
				</div>
				<div class="row">
					<div class="col text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</div>
			</div>
		</form>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>