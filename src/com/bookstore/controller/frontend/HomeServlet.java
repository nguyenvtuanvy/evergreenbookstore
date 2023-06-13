package com.bookstore.controller.frontend;

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

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		
		List<Book> listnewbook = bookDao.listnewbook();
		List<Book> listbestsellingbooks = bookDao.listBestSellingBooks();
		List<Book> listmostfavoredbooks = bookDao.listMostFavoredBooks();
		
		request.setAttribute("listnewbook", listnewbook);
		request.setAttribute("listbestsellingbooks", listbestsellingbooks);
		request.setAttribute("listmostfavoredbooks", listmostfavoredbooks);
		
		String homePage = "frontend/index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(homePage);
		rd.forward(request, response);
	}

}
