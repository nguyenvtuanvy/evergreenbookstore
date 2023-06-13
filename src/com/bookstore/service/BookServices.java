package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	private CategoryDAO categoryDAO;
	private BookDao bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		bookDao = new BookDao();
		categoryDAO = new CategoryDAO();
	}
	
	public void listbook() throws ServletException, IOException {
		listbook(null);
	}

	public void listbook(String message) throws ServletException, IOException {
		List<Book> listbooks = bookDao.listAll();

		request.setAttribute("listbooks", listbooks);
		
		if (message != null) {
			request.setAttribute("message", message);
		}

		String listPage = "book_list.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(listPage);
		rd.forward(request, response);
	}

	public void showBookNewForm() throws ServletException, IOException {
		List<Category> listcategory = categoryDAO.listAll();

		request.setAttribute("listcategory", listcategory);

		String newPage = "book_form.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(newPage);
		rd.forward(request, response);
	}

	public void createbook() throws ServletException, IOException {
		String title = request.getParameter("title");
		
		Book exitbook = bookDao.findByTitle(title);
		
		if (exitbook != null) {
			String message = "Could not create new book because the title "+title+" already exists.";
			listbook(message);
			return;
		}
		
		Book newbook = new Book();
		readBookFields(newbook);
		
		Book createbook = bookDao.create(newbook);
		
		if (createbook.getBookId() > 0) {
			String message = "A new Book has been created susscessfully";
			listbook(message);
		}
	}

	public void readBookFields(Book book) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String depcription = request.getParameter("depcription");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
	

		DateFormat datefomart = new SimpleDateFormat("yyyy-MM-dd");
		Date publishDate = null;
		try {
			publishDate = datefomart.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException("Error parsing publish date (fomart is yyyy-MM-dd)");
		}

		book.setTitle(title);
		book.setAuthor(author);
		book.setIsbn(isbn);
		book.setDepcription(depcription);
		book.setPublishDate(publishDate);
		int categoryid = Integer.parseInt(request.getParameter("category"));
		Category category = categoryDAO.get(categoryid);
		book.setCategory(category);
		book.setPrice(price);
		Part part = request.getPart("bookImage");

		if (part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] imageBytes = new byte[(int) size];

			InputStream inputStream = part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			
			book.setImage(imageBytes);
		}
	}
	public void editbook() throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("id"));
		Book book = bookDao.get(bookid);
		List<Category> listcategory = categoryDAO.listAll();

		request.setAttribute("book", book);
		request.setAttribute("listcategory", listcategory);
		
		String editPage = "book_form.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(editPage);
		rd.forward(request, response);
	}

	public void updatebook() throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		Book existbook = bookDao.get(bookid);
		Book bookbytitle = bookDao.findByTitle(title);
		
		if (bookbytitle != null && !existbook.equals(bookbytitle)) {
			String message = "Could not update book because there's another book having same title";
			listbook(message);
			return;
		}
		
		readBookFields(existbook);
		
		bookDao.update(existbook);
		
		String message = "The Book has been updated susscessfully";
		listbook(message);	
	}

	public void deletebook() throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("id"));
		
		bookDao.delete(bookid);
		
		String message = "The Book has been deleted susscessfully";
		listbook(message);	
	}

	public void listBooksByCategory() throws ServletException, IOException {
		int categoryid = Integer.parseInt(request.getParameter("id")); 
		System.out.println(categoryid);
		List<Book> listbooks = bookDao.listByCategory(categoryid);
		Category category = categoryDAO.get(categoryid);
		
		request.setAttribute("listbooks", listbooks);
		request.setAttribute("category", category);
		
		String listPage = "frontend/books_list_by_category.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(listPage);
		rd.forward(request, response);
	}

	public void viewdetailbook() throws ServletException, IOException {
		int bookid = Integer.parseInt(request.getParameter("id"));
		
		Book book = bookDao.get(bookid);

		request.setAttribute("book", book);
		
		String detailPage = "frontend/book_detail.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(detailPage);
		rd.forward(request, response);
	}

	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Book> result = null;
		
		if (keyword.equals("")) {
			result = bookDao.listAll();
		} else {
			result = bookDao.listsearch(keyword);
		}
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		
		String searchPage = "frontend/search_result.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(searchPage);
		rd.forward(request, response);
	}

}
