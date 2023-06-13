<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Review Posted" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<form action="submit_review" method="post" style="max-width: 900px; margin: 0 auto;">
			<div class="row">
				<div class="col">
					<h2>Your Review</h2>
				</div>
				<div class="col">&nbsp;</div>
				<div class="col">
					<h2>${loggedcustomer.fullname}</h2>
				</div>
			</div>
			<div class="row">
				<div class="col"><hr/></div>
			</div>
			<div class="row">
				<div class="col-sm">
					<h4>${book.title}</h4> 
					<img class="img-fluid" src="data:image/jpg;base64,${book.base64Image}" />
				</div>
				<div class="col-sm">
					<h3>Your Review has been posted. Thank You!</h3>
				</div>
			</div>
		</form>

		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>