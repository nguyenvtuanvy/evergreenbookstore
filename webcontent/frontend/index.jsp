<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param value="Evergreen Books" name="pageTitle"/>
	</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row text-center">
			<div class="col">
				<h2>New Books</h2>
			</div>
		</div>

		<div class="row justify-content-center mb-3">
			<c:forEach items="${listnewbook}" var="book">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>

		<div class="row text-center">
			<div class="col">
				<h2>Best Selling Books</h2>
			</div>
		</div>

		<div class="row justify-content-center mb-3">
			<c:forEach items="${listbestsellingbooks}" var="book">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>

		<div class="row text-center">
			<div class="col">
				<h2>Most Favored Books</h2>
			</div>
		</div>

		<div class="row justify-content-center mb-3">
			<c:forEach items="${listmostfavoredbooks}" var="book">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>