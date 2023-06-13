package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.ReviewTable;

public class ReviewServices {
	private ReviewDao reviewDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public ReviewServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		reviewDao = new ReviewDao();
	}

	public void listreview() throws ServletException, IOException{
		listreview(null);
	}
	
	public void listreview(String message) throws ServletException, IOException {
		List<ReviewTable> listreview = reviewDao.listAll();
		request.setAttribute("listreview", listreview);

		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "review_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(listPage);
		rd.forward(request, response);
	}

	public void editreview() throws ServletException, IOException {
		int reviewid = Integer.parseInt(request.getParameter("id"));
		ReviewTable review = reviewDao.get(reviewid);

		request.setAttribute("review", review);

		String editPage = "review_form.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(editPage);
		rd.forward(request, response);
	}

	public void updatereview() throws ServletException, IOException {
		int reviewid = Integer.parseInt(request.getParameter("reviewId"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		ReviewTable review = reviewDao.get(reviewid);
		review.setHeadline(headline);
		review.setComment(comment);

		reviewDao.update(review);
		
		String message = "The review has been updated susccessfully";
		listreview(message);
	}

	public void deletereview() throws ServletException, IOException {
		int reviewid = Integer.parseInt(request.getParameter("id"));
		reviewDao.delete(reviewid);
		
		String message = "The review has been deleted susccessfully";
		listreview(message);
	}

	public void showReviewform() throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("book_id"));
		BookDao bookDao = new BookDao();
		Book book = bookDao.get(bookid);
		
		HttpSession session = request.getSession();
		session.setAttribute("book", book);

		Customer customer = (Customer) session.getAttribute("loggedcustomer");
		
		ReviewTable exitReview = reviewDao.findByCutomerAndBook(customer.getCustomerId(), bookid);
		
		String targetPage = "frontend/review_form.jsp";
		
		if (exitReview != null) {
			request.setAttribute("review", exitReview);
			targetPage = "frontend/review_info.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(targetPage);
		rd.forward(request, response);
	}

	public void submitreview() throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("bookId"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		ReviewTable newreview = new ReviewTable();
		newreview.setHeadline(headline);
		newreview.setComment(comment);
		newreview.setRating(rating);
		
		Book book = new Book();
		book.setBookId(bookid);
		newreview.setBook(book);
		
		Customer customer = (Customer) request.getSession().getAttribute("loggedcustomer");
		newreview.setCustomer(customer);
		
		reviewDao.create(newreview);
		
		String messagePage = "frontend/review_done.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(messagePage);
		rd.forward(request, response);
	}

}
