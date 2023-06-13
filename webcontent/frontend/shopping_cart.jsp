<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Your Shopping Cart" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Your Cart Details</h2>
			</div>
		</div>

		<c:if test="${message != null}">
			<div class="row">
				<div class="col text-center">
					<h4>${message}</h4>
				</div>
			</div>
		</c:if>

		<c:set var="cart" value="${sessionScope['cart']}" />

		<c:if test="${cart.totalItems == 0}">
			<div class="row">
				<div class="col text-center">
					<h4>There's no items in your cart</h4>
				</div>
			</div>
		</c:if>

		<c:if test="${cart.totalItems > 0}">
			<form action="update_cart" method="post" style="max-width: 600px; margin: 0 auto;">
				<c:forEach items="${cart.items}" var="item" varStatus="status">
					<div class="row border rounded p-1">
						<div class="col-sm">
							<div>${status.index + 1}</div>
						</div>
						<div class="col-5">
							<img class="img-fluid" src="data:image/jpg;base64,${item.key.base64Image}" />
						</div>
						
						<div class="col-6">
						  	<div><h5>${item.key.title}</h5></div>
						
							<div>
								<input type="hidden" name="bookid" value="${item.key.bookId}" /> 
								<input type="number" name="quantity${status.index + 1}" value="${item.value}" class="form-control" style="max-width: 50px;" size="5" required step="1" min="1" />
							</div>
						
							<div>
							    X <fmt:formatNumber value="${item.key.price}" type="currency" />
							</div>
						
							<div>
								= <span class="h5"><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></span>
							</div>
						
							<div>
								<a href="remove_form_cart?book_id=${item.key.bookId}">Remove</a>
							</div>
						</div>
					</div>
					<div class="row">&nbsp;</div>
				</c:forEach>

				<div class="row">
					<div class="col-12 text-center">
						<b>${cart.totalQuantity} book(s)</b>
						&nbsp;&nbsp;
						<span class="h4">Total: <b><fmt:formatNumber value="${cart.totalAmount}" type="currency" /></b></span> 
					</div>
				</div>
					
				<div class="row">&nbsp;</div>

				<div class="row">
					<div class="col-12 text-center">
						<button type="submit" class="btn btn-primary">Update</button>
						&nbsp;&nbsp;
						<input type="button" class="btn btn-secondary" id="clearCart" value="Clear Cart" />
					</div>
				</div>

				<div class="row">&nbsp;</div>

				<div class="row">
					<div class="col-12 text-center">
						<a href="${pageContext.request.contextPath}/">Continue Shopping</a>
						&nbsp;&nbsp;
						<a href="checkout">CheckOut</a>
					</div>
				</div>
			</form>
		</c:if>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	
	$(document).ready(function() {
		$("#clearCart").click(function() {
			window.location = 'clear_cart';
		});
	});
</script>
</html>