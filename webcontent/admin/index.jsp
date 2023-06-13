<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergreen Bookstore Administration</title>
<link rel="stylesheet" href="../css/style_admin.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<h2 class="pageheading">Administration Dashboard</h2>
	</div>

	<div align="center">
		<h2 class="pageheading">Quick Actions</h2>
		<b> 
			<a href="new_book">New Book</a> &nbsp; 
			<a href="user_form.jsp">New User</a> &nbsp;
			<a href="category_form.jsp">New Category</a> &nbsp; 
			<a href="new_customer">New Customer</a>
		</b>
	</div>

	<div align="center">
		<h2 class="pageheading">Recent Sales:</h2>
		<table border="1" cellPadding="5">
			<tr>
				<th>Order ID</th>
				<th>Ordered by</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
			</tr>
			<c:forEach items="${listMostRecentSales}" var="item">
				<tr>
					<td><a href="view_order?id=${item.orderId}">${item.orderId}</a></td>
					<td>${item.customer.fullname}</td>
					<td>${item.bookCopies}</td>
					<td><fmt:formatNumber value="${item.orderTotal}"
							type="currency" /></td>
					<td>${item.paymentMethod}</td>
					<td>${item.orderStatus}</td>
					<td>${item.orderDate}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div align="center">
		<h2 class="pageheading">Recent Reviews:</h2>
		<table border="1">
			<tr>
				<th>Book</th>
				<th>Rating</th>
				<th>Headline</th>
				<th>Customer</th>
				<th>Review On</th>
			</tr>

			<c:forEach items="${listMostRecent}" var="item">
				<tr>
					<td>${item.book.title}</td>
					<td>${item.rating}</td>
					<td><a href="edit_review?id=${item.reviewId}">${item.headline}</a></td>
					<td>${item.customer.fullname}</td>
					<td>${item.reviewTime}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div align="center">
		<h2 class="pageheading">Statistis:</h2>
		Total Users: ${totalusers} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Books:
		${totalbooks} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Customers:
		${totalcustomers} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Reviews:
		${totalreviews} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Total Orders:
		${totalorders} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>