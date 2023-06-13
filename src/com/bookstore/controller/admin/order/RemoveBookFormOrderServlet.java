package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

@WebServlet("/admin/remove_book_from_order")
public class RemoveBookFormOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveBookFormOrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		BookOrder order = (BookOrder) session.getAttribute("order");
		
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		Iterator<OrderDetail> iterator = orderDetails.iterator();
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			
			if (orderDetail.getBook().getBookId() == bookId) {
				float newtotal = order.getOrderTotal() - orderDetail.getSubtotal();
				order.setOrderTotal(newtotal);
				iterator.remove();
			}
		}
		String editOrderForm = "order_form.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(editOrderForm);
		rd.forward(request, response);
	}

}
