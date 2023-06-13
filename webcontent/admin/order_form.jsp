<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="../css/style_admin.css" />
<title>Edit Order Details - Evergreen Bookstore Administration</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<form action="update_order" method="post">
		<div align="center">
			<table>
				<tr>
					<td>Ordered By:</td>
					<td>${order.customer.fullname}</td>
				</tr>
				<tr>
					<td>Order Date:</td>
					<td>${order.orderDate}</td>
				</tr>
				<tr>
					<td>Payment Method:</td>
					<td><select name="paymentMethod">
							<option value="Cash On Delivery"
								<c:if test="${order.paymentMethod eq 'Cash On Delivery'}">selected='selected'</c:if>>Cash
								On Delivery</option>
							<option value="paypal"
								<c:if test="${order.paymentMethod eq 'paypal'}">selected='selected'</c:if>>PayPal
								or Credit Card</option>
					</select></td>
				</tr>
				<tr>
					<td>Order Status:</td>
					<td><select name="orderStatus">
							<option value="Processing"
								<c:if test="${order.orderStatus eq 'Processing' }">selected='selected'</c:if>>Processing</option>
							<option value="Shipping"
								<c:if test="${order.orderStatus eq 'Shipping' }">selected='selected'</c:if>>Shipping</option>
							<option value="Delivered"
								<c:if test="${order.orderStatus eq 'Delivered' }">selected='selected'</c:if>>Delivered</option>
							<option value="Completed"
								<c:if test="${order.orderStatus eq 'Completed' }">selected='selected'</c:if>>Completed</option>
							<option value="Cancelled"
								<c:if test="${order.orderStatus eq 'Cancelled' }">selected='selected'</c:if>>Cancelled</option>
					</select></td>
				</tr>
			</table>
			<table>
				<h2>Recipient Information</h2>
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstname" required minlength="3"
						maxlength="30" value="${order.firstname}" size="45" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" name="lastname" required minlength="3"
						maxlength="30" value="${order.lastname}" size="45" /></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td><input type="text" name="phone" required minlength="10"
						maxlength="15" value="${order.phone}" size="45" /></td>
				</tr>
				<tr>
					<td>Address Line 1:</td>
					<td><input type="text" name="address1" required minlength="10"
						maxlength="256" value="${order.addressLine1}" size="45" /></td>
				</tr>
				<tr>
					<td>Address Line 2:</td>
					<td><input type="text" name="address2" required minlength="10"
						maxlength="256" value="${order.addressLine2}" size="45" /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><input type="text" name="city" required minlength="3"
						maxlength="32" value="${order.city}" size="45" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><input type="text" name="state" required minlength="3"
						maxlength="45" value="${order.state}" size="45" /></td>
				</tr>
				<tr>
					<td>Zipcode:</td>
					<td><input type="text" name="zipcode" required minlength="3"
						maxlength="24" value="${order.zipcode}" size="45" /></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><select name="country" required>
							<c:forEach items="${mapCountries}" var="country">
								<option value="${country.value}"
									<c:if test='${order.country eq country.value}'>selected='selected'</c:if>>${country.key}</option>
							</c:forEach>
					</select></td>
				</tr>

			</table>
		</div>

		<div align="center">
			<h2>Ordered Book</h2>
			<table border="1">
				<tr>
					<th>Index</th>
					<th>Book Title</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Subtotal</th>
					<th></th>
				</tr>
				<c:forEach items="${order.orderDetails}" var="o" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${o.book.title}</td>
						<td>${o.book.author}</td>
						<td><input type="hidden" name="price" value="${o.book.price}" />
							<fmt:formatNumber value="${o.book.price}" type="currency" /></td>
						<td><input type="hidden" name="bookId"
							value="${o.book.bookId}" /> <input type="number"
							name="quantity${status.index + 1}" value="${o.quantity}" size="5"
							step="1" min="1" required /></td>
						<td><fmt:formatNumber value="${o.subtotal}" type="currency" />
						</td>
						<td><a href="remove_book_from_order?id=${o.book.bookId}">Remove</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="7" align="right">
						<p>
							Subtotal:
							<fmt:formatNumber value="${order.subtotal}" type="currency" />
						</p>
						<p>
							Tax: <input type="number" size="5" name="tax" step="0.1"
								min="0.0 
								value=" ${order.tax}" required />
						</p>
						<p>
							Shipping Fee: <input type="number" size="5" name="shippingfee"
								step="0.1" min="0.0
								value="
								${order.shippingFee}" required />
						</p>
						<p>
							TOTAL:
							<fmt:formatNumber value="${order.orderTotal}" type="currency" />
						</p>
					</td>
				</tr>
			</table>
		</div>
		<div align="center">
			<br /> <a href="javascript:showAddBookPopup()"><b>Add Books</b></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
				value="Save">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
				value="Cancel"
				onclick="javascript:window.location.href='list_order';">
		</div>
	</form>
	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		function showAddBookPopup() {
			var width = 600;
			var height = 250;
			var left = (screen.width - width) / 2;
			var top = (screen.height - height) / 2;
			window.open('add_book_form', '_blank', 'width=' + width
					+ ', height=' + height + ', top=' + top + ', left=' + left);
		}
	</script>
</body>

</html>