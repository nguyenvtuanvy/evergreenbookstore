<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="${pageTitle}" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">&nbsp;</div>
		
		<div class="row">
			<div class="col text-center">
				<h3>${message}</h3>
			</div>
		</div>


		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>