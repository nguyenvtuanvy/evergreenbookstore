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
<title>Manage Details - Evergreen Bookstore Administration</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Details of Order ID: ${order.orderId}</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<jsp:directive.include file="../common/order_detail.jsp" />
	
	<div align="center">
		<br/>
		<a href="edit_order?id=${order.orderId}">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">Detelt this Order</a>
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
																	reviewid = $(
																			this)
																			.attr(
																					"id");
																	if (confirm("Are you sure you want to delete the user ID "
																			+ reviewid
																			+ "?")) {
																		window.location = "delete_review?id="
																				+ reviewid;
																	}
																});
											});
						});
	</script>
</body>
</html>
