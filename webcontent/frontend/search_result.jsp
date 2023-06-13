<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Result for ${keyword}" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">&nbsp;</div>

		<div class="row">
			<div class="col text-center">
				<c:if test="${fn:length(result) == 0}">
					<h2>No Results for " ${keyword} "</h2>
				</c:if>

				<c:if test="${fn:length(result) > 0}">
					<h2>Results for " ${keyword} "</h2>
				</c:if>
			</div>
		</div>

		<c:forEach items="${result}" var="book">
			<div class="row">
				<div class="col-sm-2 text-center">
					<a href="view_book?id=${book.bookId}"> <img width="128" height="164"
						src="data:image/jpg;base64,${book.base64Image}" class="book_small" />
					</a>
				</div>
				<div class="col-sm-8">
					<div>
						<h3>
							<a href="view_book?id=${book.bookId}">${book.title}</a>
						</h3>
					</div>
					<div><jsp:directive.include file="book_rating.jsp" /></div>
					<div>
						<i>${book.author}</i>
					</div>
					<div>
						<c:set var="shortDescription" value="${fn:substring(book.depcription, 0, 100)}" />
						<p>${fn:escapeXml(shortDescription)}...</p>
					</div>
				</div>
				<div class="col-sm-2">
					<h3>$${book.price}</h3>
					<a href="add_to_cart?book_id=${book.bookId}" class="btn btn-primary">
						<h3>Add to Cart</h3>
					</a>
				</div>
			</div>
			<div class="row">&nbsp;</div>
		</c:forEach>

		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>