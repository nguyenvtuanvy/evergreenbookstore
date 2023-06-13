package com.bookstore.service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.CustomerDao;
import com.bookstore.entity.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CustomerService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		customerDao = new CustomerDao();
	}

	public void listcustomer(String message) throws ServletException, IOException {
		List<Customer> listcustomer = customerDao.listAll();

		if (message != null) {
			request.setAttribute("message", message);
		}

		request.setAttribute("listcustomer", listcustomer);

		String listPage = "customer_list.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(listPage);
		rd.forward(request, response);
	}

	public void listcustomer() throws ServletException, IOException {
		listcustomer(null);
	}

	public void createcustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer customer = customerDao.findbyemail(email);

		if (customer != null) {
			String message = "Could not create the email " + email + "is already registered by another customer";
			request.setAttribute("message", message);
			listcustomer(message);
		} else {
			Customer newcustomer = new Customer();
			updatecustomerfieldsform(newcustomer);
			customerDao.create(newcustomer);

			String message = "New customer has been created successfully";
			listcustomer(message);
		}
	}

	private void updatecustomerfieldsform(Customer customer) {
		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phoneNumber");
		String addressLine1 = request.getParameter("address1");
		String addressLine2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipCode");
		String country = request.getParameter("country");
		
		if (email != null && !email.equals("")) {
			customer.setEmail(email);
		} 
		
		customer.setFirstname(firstname);
		customer.setLastname(lastname);
		
		if (password != null && !password.equals("")) {
			customer.setPassword(password);
		} 
		
		customer.setAddressLine1(addressLine1);
		customer.setAddressLine2(addressLine2);
		customer.setPhoneNumber(phone);
		customer.setCity(city);
		customer.setState(state);
		customer.setZipCode(zipcode);
		customer.setCountry(country);
	}

	public void editcustomer() throws ServletException, IOException {
		int customerid = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDao.get(customerid);

		request.setAttribute("customer", customer);

		CommonUtility.getCountryList(request);
		
		String editPage = "customer_form.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(editPage);
		rd.forward(request, response);

	}

	public void updatecustomer() throws ServletException, IOException {
		int customerid = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");

		Customer exitcustomer = customerDao.findbyemail(email);

		if (exitcustomer != null && customerid != exitcustomer.getCustomerId()) {
			String message = "Could not update customer, because customer with email " + email + " already exists";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		} else {
			Customer updatecustomer = customerDao.get(customerid);
			updatecustomerfieldsform(updatecustomer);
			customerDao.update(updatecustomer);

			String message = "Customer has been update succesfull";
			listcustomer(message);
		}
	}

	public void deletecustomer() throws ServletException, IOException {
		int customerid = Integer.parseInt(request.getParameter("id"));
		System.out.println(customerid);
		customerDao.delete(customerid);

		String message = "Customer has been delete succsesfully";
		listcustomer(message);
	}

	public void registercustomer() throws ServletException, IOException {
		String email = request.getParameter("email");
		Customer exitcustomer = customerDao.findbyemail(email);
		String message = "";
		if (exitcustomer != null) {
			message = "Could not register the email " + email + "is already registered by another customer";
		} else {
			Customer newcustomer = new Customer();
			updatecustomerfieldsform(newcustomer);
			customerDao.create(newcustomer);

			message = "You have registered successfully! Thanks You<br/>" + "<a href='login'>Click here</a> to login";
		}
		String messagePage = "frontend/message.jsp";
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher(messagePage);
		rd.forward(request, response);
	}

	public void showlogin() throws ServletException, IOException {
		String loginPage = "frontend/login.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(loginPage);
		rd.forward(request, response);
	}

	public void dologin() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Customer customer = customerDao.checklogin(email, password);
		
		if (customer == null) {
			String message = "Login failed.Please check your email and password";
			request.setAttribute("message", message);
			showlogin();
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedcustomer", customer);
			Object objRedirectURL = session.getAttribute("redirectURL");
			
			if (objRedirectURL != null) {
				String redirectURL = (String) objRedirectURL;
				session.removeAttribute("redirectURL");
				response.sendRedirect(redirectURL);
			} else {
				showcustomerprofile();
			}
		}
	}

	public void showcustomerprofile() throws ServletException, IOException {
		String customerprofilePage = "frontend/customer_profile.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(customerprofilePage);
		rd.forward(request, response);
	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		CommonUtility.getCountryList(request);
		
		String customerprofilePage = "frontend/edit_profile.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(customerprofilePage);
		rd.forward(request, response);
	}

	public void updatecustomerprofile() throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedcustomer");
		updatecustomerfieldsform(customer);
		customerDao.update(customer);
		showcustomerprofile();
	}

	public void newCustomer() throws ServletException, IOException {
		CommonUtility.getCountryList(request);
		
		String customerForm = "customer_form.jsp";
		request.getRequestDispatcher(customerForm).forward(request, response);
	}
	
	public void showCustomerRegistrationForm() throws ServletException, IOException {
		CommonUtility.getCountryList(request);
		
		String registerPage = "frontend/register_form.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(registerPage);
		rd.forward(request, response);
	}
}
