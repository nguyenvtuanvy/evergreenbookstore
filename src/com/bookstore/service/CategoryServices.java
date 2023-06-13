package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categorydao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categorydao = new CategoryDAO();
	}

	public void listcategory() throws ServletException, IOException {
		listcategory(null);
	}

	public void listcategory(String messega) throws ServletException, IOException {
		List<Category> listcategory = categorydao.listAll();

		request.setAttribute("listcategory", listcategory);
		
		if(messega != null) {
			request.setAttribute("message", messega);
		}

		String listpage = "category_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(listpage);
		rd.forward(request, response);
	}

	public void createCatrgory() throws ServletException, IOException {
		String name = request.getParameter("name");

		Category exitcategory = categorydao.findbyname(name);

		if (exitcategory != null) {
			String message = "Could not create category. A category with name " + name + " already exists";

			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		} else {
			Category category = new Category(name);
			categorydao.create(category);
			
			listcategory("New category created succesfully");
		}
	}

	public void editcategory() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Category category = categorydao.get(id);
		
		String editpage = "category_form.jsp";
		request.setAttribute("category", category);
		RequestDispatcher rd = request.getRequestDispatcher(editpage);
		rd.forward(request, response);
	}

	public void updatecategory() throws ServletException, IOException {
		int cateid = Integer.parseInt(request.getParameter("categoryId"));
		String catename = request.getParameter("name");
		System.out.println(catename);
		
		Category catefindbyid = categorydao.get(cateid);
		
		Category catefindbyname = categorydao.findbyname(catename);
		
		if (catefindbyname != null && catefindbyid.getCategoryId() != catefindbyname.getCategoryId()) {
			String message = "Could not update category, because category with name "+catename+" already exists";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		} else {
			Category category = new Category(cateid, catename);
			categorydao.update(category);
			
			String message = "Category has been updated succesfully";
			listcategory(message);
		}
		
		
	}

	public void deletecategory() throws ServletException, IOException {
		int cateid = Integer.parseInt(request.getParameter("id")); 
		BookDao bookDao = new BookDao();
		long numberofbook = bookDao.countbycategory(cateid);
		String message;
		if (numberofbook > 0) {
			message = "Could not delete the category (ID = %d) because it currently contains some books.";
			message = String.format(message, numberofbook);
		} else {
			categorydao.delete(cateid);
			message = "Category has been deleted successfully";
		}
		listcategory(message);
	}

}
