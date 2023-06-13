<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="${book.title}" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">
			<div class="col-12">
				<h2 id="book_title">${book.title}</h2>
				by <span id="book_author"> ${book.author} </span>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-2">
				<img class="img-fluid"
					src="data:image/jpg;base64,${book.base64Image}" />
			</div>
			<div class="col-sm-8">
				<div>
					<jsp:directive.include file="book_rating.jsp" />
					&nbsp;&nbsp;
				</div>
				<div>${book.depcription}</div>
			</div>
			<div class="col-sm-2 text-center">
				<div>
					<h2>$${book.price}</h2>
				</div>
				<br />
				<div>
					<button id="buttonAddtoCart" class="btn btn-primary">Add
						to Cart</button>
				</div>
			</div>
		</div>

		<div class="row">&nbsp;</div>

		<div class="row">
			<div class="col-12 text-center">
				<h3>
					<a id="reviews">Customer Reviews</a>
				</h3>
				&nbsp;
				<button id="buttonWrite" class="btn btn-secondary" >Write a
					Customer Review</button>
			</div>
		</div>

		<div class="row">&nbsp;</div>


		<c:forEach items="${book.reviewTables}" var="review">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-8">
					<c:forTokens items="${review.stars}" delims="," var="star">
						<c:if test="${star eq 'on'}">
							<img src="images/rating_on.png">
						</c:if>
						<c:if test="${star eq 'off'}">
							<img src="images/rating_off.png">
						</c:if>
					</c:forTokens>
					- <b>${review.headline}</b>
				</div>
				<div class="col-sm-1"></div>
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-8">by ${review.customer.fullname} on
					${review.reviewTime}</div>
				<div class="col-sm-1"></div>
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-8">
					<i>${review.comment}</i>
				</div>
				<div class="col-sm-1"></div>
			</div>

			<div class="row">&nbsp;</div>
		</c:forEach>


		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#buttonWrite").click(function() {
				window.location = 'write_review?book_id=' + ${book.bookId};
			});
			$("#buttonAddtoCart").click(function() {
				window.location = 'add_to_cart?book_id=' + ${book.bookId};
			});
		});
	</script>
</body>
</html>