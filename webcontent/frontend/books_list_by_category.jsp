<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="page_head.jsp">
		<jsp:param value="Books in ${category.name}" name="pageTitle"/>
	</jsp:include>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">
			<div class="col text-center"><h1>${category.name}</h1></div>			
		</div>

		<div class="row">
			<c:forEach items="${listbooks}" var="book">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>

		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>