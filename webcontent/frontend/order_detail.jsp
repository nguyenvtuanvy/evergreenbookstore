<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="My Order Details" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="row">&nbsp;</div>
		<c:if test="${order == null}">
			<div class="row">
				<div class="col text-center">
					<h2>Sorry, you are not authorized to view this order</h2>
				</div>
			</div>
		</c:if>

		<c:if test="${order != null}">
			<div class="row">
				<div class="col text-center">
					<h2>Your Order ID: ${order.orderId}</h2>
				</div>
			</div>

			<jsp:directive.include file="../common/order_detail.jsp" />
		</c:if>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
