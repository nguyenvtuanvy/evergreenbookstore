package com.bookstore.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;

@WebServlet("/add_to_cart")
public class AddBookToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddBookToCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("book_id"));
		
		Object Cartobject = request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart = null;
		if (Cartobject != null && Cartobject instanceof ShoppingCart) {
			shoppingCart = (ShoppingCart) Cartobject;
		} else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}
		
		BookDao bookDao = new BookDao();
		Book book = bookDao.get(bookid);
		
		shoppingCart.addItem(book);
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
