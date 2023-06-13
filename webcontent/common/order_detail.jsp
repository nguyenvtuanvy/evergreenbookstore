<div class="row">
	<div class="col-sm-2"></div>

	<div class="col-sm-8">
		<div class="card">
			<div class="card-header">
				<div class="text-center">
					<h3>Order Overview</h3>
				</div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col">
						<b> Ordered By:</b>
					</div>
					<div class="col">${order.customer.fullname}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Order Status:</b>
					</div>
					<div class="col">${order.orderStatus}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Order Date:</b>
					</div>
					<div class="col">${order.orderDate}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Payment Method:</b>
					</div>
					<div class="col">${order.paymentMethod}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Book Copies:</b>
					</div>
					<div class="col">${order.bookCopies}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Total Amount:</b>
					</div>
					<div class="col">
						<fmt:formatNumber value="${order.orderTotal}" type="currency" />
					</div>
				</div>
			</div>
		</div>

		<div class="row">&nbsp;</div>

		<div class="card">
			<div class="card-header">
				<div class="text-center">
					<h3>Recipient Infomation</h3>
				</div>
			</div>
			<div class="card-body">
				<div class="row">
					<div class="col">
						<b>First Name:</b>
					</div>
					<div class="col">${order.firstname}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Last Name:</b>
					</div>
					<div class="col">${order.lastname}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Phone:</b>
					</div>
					<div class="col">${order.phone}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Address Line 1:</b>
					</div>
					<div class="col">${order.addressLine1}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Address Line 2:</b>
					</div>
					<div class="col">${order.city}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Sate:</b>
					</div>
					<div class="col">${order.state}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Country:</b>
					</div>
					<div class="col">${order.countryName}</div>
				</div>
				<div class="row">
					<div class="col">
						<b>Zipcode:</b>
					</div>
					<div class="col">${order.zipcode}</div>
				</div>
			</div>
		</div>

		<div class="row">&nbsp;</div>

		<div class="card">
			<div class="card-header">
				<div class="text-center">
					<h3>Ordered Book</h3>
				</div>
			</div>
			<div class="card-body">
				<c:forEach items="${order.orderDetails}" var="o" varStatus="status">
					<div class="row">
						<div class="col-sm">${status.index + 1}</div>
						<div class="col-5">
							<img width="128" height="164"
								src="data:image/jpg;base64,${o.book.base64Image}" />
						</div>
						<div class="col-6">
							<div>
								<h6>${o.book.title}</h6>
							</div>
							<div>
								by <i>${o.book.author}</i>
							</div>
							<div>
								<fmt:formatNumber value="${o.book.price}" type="currency" />
							</div>
							<div>X ${o.quantity}</div>
							<div>
								= <b><fmt:formatNumber value="${o.subtotal}" type="currency" /></b>
							</div>
						</div>
					</div>
					<div class="row">&nbsp;</div>
				</c:forEach>
			</div>
		</div>

		<div class="row">&nbsp;</div>

		<div class="card">
			<div class="card-header">
				<div class="text-center">
					<h3>Summary</h3>
				</div>
			</div>
			<div class="card-body">
				<div class="col text-center">
					<p>
						Subtotal:
						<fmt:formatNumber value="${order.subtotal}" type="currency" />
					</p>
					<p>
						Tax:
						<fmt:formatNumber value="${order.tax}" type="currency" />
					</p>
					<p>
						Shipping Fee:
						<fmt:formatNumber value="${order.shippingFee}" type="currency" />
					</p>
					<p>
						TOTAL:
						<fmt:formatNumber value="${order.orderTotal}" type="currency" />
					</p>
				</div>
			</div>
		</div>
	</div>

	<div class="col-sm-2"></div>
</div>