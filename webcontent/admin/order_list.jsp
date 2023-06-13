<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="../css/style_admin.css" />
<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<title>Manage Orders - Evergreen Bookstore Administration</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Book Order Management</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<div align="center">
		<table border="1" cellPadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Action</th>
			</tr>

			<c:forEach var="o" items="${listorder}" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${o.orderId}</td>
					<td>${o.customer.fullname}</td>
					<td>${o.bookCopies}</td>
					<td> <fmt:formatNumber value="${o.orderTotal}" type="currency" /></td>
					<td>${o.paymentMethod}</td>
					<td>${o.orderStatus}</td>
					<td>${o.orderDate}</td>
					<td><a href="view_order?id=${o.orderId}">Details</a> <a
						href="edit_order?id=${o.orderId}">Edit</a> <a
						href="javascript:void(0);" class="deletelink" id="${o.orderId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		$(document)
				.ready(
						function() {
							$(".deletelink")
									.each(
											function() {
												$(this)
														.on(
																"click",
																function() {
																	orderid = $(
																			this)
																			.attr(
																					"id");
																	if (confirm("Are you sure you want to delete the Book ID "
																			+ orderid
																			+ "?")) {
																		window.location = "delete_order?id="
																				+ orderid;
																	}
																});
											});
						});
	</script>
</body>
</html>
