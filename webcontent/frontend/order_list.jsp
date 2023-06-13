<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="My Orders History" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">&nbsp;</div>
		
		<div class="row">
			<div class="col text-center"><h3>My Oder History</h3></div>
		</div>

		<c:if test="${fn:length(listorders) == 0}">
			<div class="row">
				<div class="col text-center"><h3>You have not placed any orders</h3></div>
			</div>
		</c:if>

		<c:if test="${fn:length(listorders) > 0}">
			<div align="center">
				<table class="table table-bordered table-striped table-hover table-responsive-sm">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Index</th>
						<th scope="col">Order ID</th>
						<th scope="col">Quantity</th>
						<th scope="col">Total Amount</th>
						<th scope="col">Order Date</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${listorders}" varStatus="status">
						<tr>
							<td scope="row">${status.index+1}</td>
							<td>${o.orderId}</td>
							<td>${o.bookCopies}</td>
							<td><fmt:formatNumber value="${o.orderTotal}"
									type="currency" /></td>
							<td>${o.orderDate}</td>
							<td>${o.orderStatus}</td>
							<td><a href="show_order_detail?id=${o.orderId}">Details</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</c:if>

		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
