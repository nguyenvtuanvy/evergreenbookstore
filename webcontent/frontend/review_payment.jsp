<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param value="Review Payment" name="pageTitle" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h5>
					<b><i>Please carefully review the following
							infomationbefore making pament</i></b>
				</h5>
			</div>
		</div>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-sm-2"></div>

			<div class="col-sm-8">
				<div class="card">
					<div class="card-header">
						<div class="text-center">
							<h3>Payer Infomation:</h3>
						</div>
					</div>
					<div class="card-body">
						<div class="form-group row">
							<label class="col col-form-lable"><b>First Name:</b></label>
							<div class="col">${payer.firstName}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>Last Name:</b></label>
							<div class="col">${payer.lastName}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>Email:</b></label>
							<div class="col">${payer.email}</div>
						</div>
					</div>
				</div>
				<div class="row">&nbsp;</div>

				<div class="card">
					<div class="card-header">
						<div class="text-center">
							<h3>Recipient Infomation:</h3>
						</div>
					</div>
					<div class="card-body">
						<div class="form-group row">
							<label class="col col-form-lable"><b>Recipient Name:</b></label>
							<div class="col">${recipient.recipientName}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>Address Line 1:</b></label>
							<div class="col">${recipient.line1}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>Address Line 2:</b></label>
							<div class="col">${recipient.line2}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>City:</b></label>
							<div class="col">${recipient.city}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>State:</b></label>
							<div class="col">${recipient.state}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>Country Code:</b></label>
							<div class="col">${recipient.countryCode}</div>
						</div>
						<div class="form-group row">
							<label class="col col-form-lable"><b>Postal Code:</b></label>
							<div class="col">${recipient.postalCode}</div>
						</div>
					</div>
				</div>

				<div class="row">&nbsp;</div>

				<div class="card">
					<div class="card-header">
						<div class="text-center">
							<h3>Transaction Details:</h3>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<b>Description: </b>${transaction.description}</div>
						<div class="row">
							<b>Item List:</b>
						</div>

						<c:forEach items="${transaction.itemList.items}" var="item"
							varStatus="status">
							<div class="row">
								<div class="col-2">${status.index + 1}</div>
								<div class="col-8">
									<div>
										<h6>${item.name}</h6>
									</div>
									<div>
										<fmt:formatNumber value="${item.price}" type="currency" />
									</div>
									<div>X ${item.quantity}</div>
									<div>
										= <b><fmt:formatNumber
												value="${item.price * item.quantity}" type="currency" /></b>
									</div>
								</div>
							</div>
							<div class="row">&nbsp;</div>
						</c:forEach>


						<div class="row">
							<div class="col text-center">
								<p>
									Subtotal:
									<fmt:formatNumber
										value="${transaction.amount.details.subtotal}" type="currency" />
								</p>
								<p>
									Tax:
									<fmt:formatNumber value="${transaction.amount.details.tax}"
										type="currency" />
								</p>
								<p>
									ShippingFee:
									<fmt:formatNumber
										value="${transaction.amount.details.shipping}" type="currency" />
								</p>
								<p>
									Total:
									<fmt:formatNumber value="${transaction.amount.total}"
										type="currency" />
								</p>
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class="col-sm-2"></div>
		</div>
	
		<div class="row">&nbsp;</div>

		<div class="row">
			<div class="col text-center">
				<form action="execute_payment" method="post">
					<input type="hidden" name="paymentId" value="${param.paymentId}" />
					<input type="hidden" name="PayerID" value="${param.PayerID}" />
					 <input type="submit" value="Pay Now" class="btn btn-primary"/>
				</form>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>