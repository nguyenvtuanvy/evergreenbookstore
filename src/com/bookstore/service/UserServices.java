package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		userDAO = new UserDAO();
	}
	
	public void listUsers() throws ServletException, IOException {
		listUsers(null);
	}
	
	public void listUsers(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("message", message);
		
		String listpage = "users_list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(listpage);
		rd.forward(request, response);
	}
	
	public void createuser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users exitsUser = userDAO.findbyemail(email);
		
		if (exitsUser != null) {
			String message = "Could not create user. A user with email " + email + " already exists";
			
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		} else {
			Users users = new Users(email, password, fullname);
			userDAO.create(users);	
			listUsers("New user created succesfully");
		}
	}

	public void edituser() throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("id"));
		Users user = userDAO.get(userid);
		
		String editpage = "user_form.jsp";
		request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher(editpage);
		rd.forward(request, response);
	}

	public void updatetuser() throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users usersbyid = userDAO.get(userid);
		
		Users usersbyemail = userDAO.findbyemail(email);
		
		if (usersbyemail != null && usersbyemail.getUserId() != usersbyid.getUserId()) {
			String message = "Could not update user, because user with email "+email+" already exists";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		} else {
			Users users = new Users(userid, email, password, fullname);
			userDAO.update(users);
			
			String message = "User has been updated succesfully";
			listUsers(message);
		}
		
	}

	public void deleteuser() throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("id"));
		userDAO.delete(userid);
		
		String message = "User has been deleted successfully";
		listUsers(message);
	}

	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean loginresult = userDAO.checkLogin(email, password);
		
		if (loginresult) {
			request.getSession().setAttribute("useremail", email);
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/");
			rd.forward(request, response);
		} else {
			System.out.println("no");
			String message = "Login Fail!";
			request.setAttribute("message", message);
			
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}
}
