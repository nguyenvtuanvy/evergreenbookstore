package com.bookstore.controller.admin.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.entity.Book;

@WebServlet("/admin/add_book_form")
public class ShowAddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAddBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		List<Book> listbook = bookDao.listAll();
		
		request.setAttribute("listbook", listbook);
		
		String editPage = "add_book_form.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(editPage);
		rd.forward(request, response);
	}

}
