package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CustomerDao;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDao;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.ReviewTable;

@WebServlet("/admin/")
public class AdminHomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminHomServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		BookDao bookDao = new BookDao();
		CustomerDao customerDao = new CustomerDao();
		ReviewDao reviewDao = new ReviewDao();
		OrderDAO orderDAO = new OrderDAO();
		
		List<BookOrder> listMostRecentSales = orderDAO.listMostRecentSales();
		List<ReviewTable> listMostRecent = reviewDao.listMostRecent();
		
		long totalusers = userDAO.count();
		long totalbooks = bookDao.count();
		long totalcustomers = customerDao.count();
		long totalreviews = reviewDao.count();
		long totalorders = orderDAO.count();
		
		request.setAttribute("listMostRecent", listMostRecent);
		request.setAttribute("listMostRecentSales", listMostRecentSales);
		
		request.setAttribute("totalusers", totalusers);
		request.setAttribute("totalbooks", totalbooks);
		request.setAttribute("totalcustomers", totalcustomers);
		request.setAttribute("totalreviews", totalreviews);
		request.setAttribute("totalorders", totalorders);
		
		String homePage = "index.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(homePage);
		rd.forward(request, response);
	}
}
