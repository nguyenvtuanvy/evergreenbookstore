package com.bookstore.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;

public class OrderService {
	private OrderDAO orderDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public OrderService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}

	public void listallorder() throws ServletException, IOException {
		listallorder(null);
	}

	public void listallorder(String message) throws ServletException, IOException {
		List<BookOrder> listorder = orderDAO.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listorder", listorder);

		String listPage = "order_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(listPage);
		rd.forward(request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException {
		int orderid = Integer.parseInt(request.getParameter("id"));

		BookOrder order = orderDAO.get(orderid);
		request.setAttribute("order", order);

		String detailPage = "order_detail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(detailPage);
		rd.forward(request, response);
	}

	public void showCheckOutForm() throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");

		// tax = subtotal * 10%
		float tax = shoppingCart.getTotalAmount() * 0.1f;

		// shippingfee = quantity * 1$;
		float shippingfee = shoppingCart.getTotalQuantity() * 1.0f;

		// total = totalamount + tax + shippingfee;
		float total = shoppingCart.getTotalAmount() + tax + shippingfee;

		session.setAttribute("tax", tax);
		session.setAttribute("shippingfee", shippingfee);
		session.setAttribute("total", total);

		CommonUtility.getCountryList(request);

		String detailPage = "frontend/checkout.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(detailPage);
		rd.forward(request, response);

	}

	public void placeorder() throws ServletException, IOException {
		String paymentMethod = request.getParameter("paymentMethod");
		BookOrder order = readOrderInfo();
		if (paymentMethod.equals("paypal")) {
			PaymentService paymentService = new PaymentService(request, response);
			request.getSession().setAttribute("order4Paypal", order);
			paymentService.authorizePayment(order);
		} else { // Cash on Delivery
			placeOrderCOD(order);
		}

	}

	public Integer placeOrderPaypal(Payment payment) {
		BookOrder order = (BookOrder) request.getSession().getAttribute("order4Paypal");
		ItemList itemList = payment.getTransactions().get(0).getItemList();
		ShippingAddress shippingAddress = itemList.getShippingAddress();
		String shippingPhoneNumber = itemList.getShippingPhoneNumber();
		
		String recipientName = shippingAddress.getRecipientName();
		String[] names = recipientName.split(" ");
		
		order.setFirstname(names[0]);
		order.setLastname(names[1]);
		order.setAddressLine1(shippingAddress.getLine1());
		order.setAddressLine2(shippingAddress.getLine2());
		order.setCity(shippingAddress.getCity());
		order.setState(shippingAddress.getState());
		order.setCountry(shippingAddress.getCountryCode());
		order.setPhone(shippingPhoneNumber);
		
		return saveOrder(order);
	}
	
	private Integer saveOrder(BookOrder order) {
		BookOrder saveOrder = orderDAO.create(order);
		
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		cart.clear();
		
		return saveOrder.getOrderId();
	}
	
	private BookOrder readOrderInfo() {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phonenumber");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String paymentMethod = request.getParameter("paymentMethod");

		BookOrder order = new BookOrder();
		order.setFirstname(firstname);
		order.setLastname(lastname);
		order.setPhone(phone);
		order.setAddressLine1(address1);
		order.setAddressLine2(address2);
		order.setCity(city);
		order.setState(state);
		order.setZipcode(zipcode);
		order.setCountry(country);
		order.setPaymentMethod(paymentMethod);

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedcustomer");
		order.setCustomer(customer);

		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		Map<Book, Integer> items = cart.getItems();

		Iterator<Book> iterator = items.keySet().iterator();

		Set<OrderDetail> orderDetails = new HashSet<>();

		while (iterator.hasNext()) {
			Book book = iterator.next();
			int quantity = items.get(book);
			float subtotal = quantity * book.getPrice();

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(book);
			orderDetail.setBookOrder(order);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);

			orderDetails.add(orderDetail);
		}

		order.setOrderDetails(orderDetails);

		float tax = (float) session.getAttribute("tax");
		float shippingfee = (float) session.getAttribute("shippingfee");
		float total = (float) session.getAttribute("total");

		order.setSubtotal(cart.getTotalAmount());
		order.setTax(tax);
		order.setShippingFee(shippingfee);
		order.setOrderTotal(total);

		return order;
	}

	private void placeOrderCOD(BookOrder order) throws ServletException, IOException {
		saveOrder(order);

		String message = "Thank you. Your order has been received. " + "We deliver your books with a few days";
		request.setAttribute("message", message);
		request.setAttribute("pageTitle", "Order Compelete");

		String messagePage = "frontend/message.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(messagePage);
		rd.forward(request, response);
	}

	public void listorderbycustomer() throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedcustomer");

		List<BookOrder> list = orderDAO.listByCustomer(customer.getCustomerId());

		request.setAttribute("listorders", list);
		String historyPage = "frontend/order_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(historyPage);
		rd.forward(request, response);
	}

	public void showorderdetailforcustomer() throws ServletException, IOException {
		int orderid = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedcustomer");

		BookOrder order = orderDAO.get(orderid, customer.getCustomerId());
		request.setAttribute("order", order);

		String detailPage = "frontend/order_detail.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(detailPage);
		rd.forward(request, response);
	}

	public void showEditOrderForm() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");

		if (isPendingBook == null) {
			BookOrder order = orderDAO.get(orderId);
			session.setAttribute("order", order);
		} else {
			session.removeAttribute("NewBookPendingToAddToOrder");
		}

		CommonUtility.getCountryList(request);

		String editPage = "order_form.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(editPage);
		rd.forward(request, response);
	}

	public void updateorder() throws ServletException, IOException {
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String phone = request.getParameter("phone");
		String addressline1 = request.getParameter("address1");
		String addressline2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");

		float shippingfee = Float.parseFloat(request.getParameter("shippingfee"));
		float tax = Float.parseFloat(request.getParameter("tax"));

		String paymentmethod = request.getParameter("paymentMethod");
		String orderttatus = request.getParameter("orderStatus");

		order.setFirstname(firstname);
		order.setLastname(lastname);
		order.setPhone(phone);
		order.setAddressLine1(addressline1);
		order.setAddressLine2(addressline2);
		order.setCity(city);
		order.setState(state);
		order.setZipcode(zipcode);
		order.setCountry(country);
		order.setShippingFee(shippingfee);
		order.setTax(tax);
		order.setPaymentMethod(paymentmethod);
		order.setOrderStatus(orderttatus);

		String[] arrayBookId = request.getParameterValues("bookId");
		String[] arrayPrice = request.getParameterValues("price");
		String[] arrayQuantity = new String[arrayBookId.length];

		for (int i = 1; i <= arrayQuantity.length; i++) {
			arrayQuantity[i - 1] = request.getParameter("quantity" + i);
		}

		Set<OrderDetail> orderDetails = order.getOrderDetails();
		orderDetails.clear();

		float toatlAmount = 0.0f;

		for (int i = 0; i < arrayBookId.length; i++) {
			int bookId = Integer.parseInt(arrayBookId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);

			float subtotal = price * quantity;

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(new Book(bookId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			orderDetail.setBookOrder(order);

			orderDetails.add(orderDetail);

			toatlAmount += subtotal;
		}

		order.setSubtotal(toatlAmount);
		toatlAmount += shippingfee;
		toatlAmount += tax;
		order.setOrderTotal(toatlAmount);

		orderDAO.update(order);

		String message = "The Order " + order.getOrderId() + " has been updated successfully";
		listallorder(message);
	}

	public void deleteorder() throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		orderDAO.delete(orderId);

		String message = "The order Id " + orderId + " has been deleted.";
		listallorder(message);
	}
}
