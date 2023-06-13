package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;

@WebServlet("/remove_form_cart")
public class RemoveBookFormCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveBookFormCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("book_id"));

		Object Cartobject = request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart = (ShoppingCart) Cartobject;

		shoppingCart.removeItem(new Book(bookid));;

		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
