package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.Customer;

public class CustomerDao extends JpaDAO<Customer> implements GenericDAO<Customer> {
	
	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		return super.create(customer);
	}

	@Override
	public Customer update(Customer customer) {
		return super.update(customer);
	}

	@Override
	public Customer get(Object id) {
		return super.find(Customer.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Customer.class, id);	
	}

	@Override
	public List<Customer> listAll() {
		return super.findwithnamequery("Customer.listAll");
	}

	@Override
	public long count() {
		return super.countwithnamequery("Customer.countAll");
	}

	public Customer findbyemail(String email) {
		List<Customer> list = super.findwithnamequery("Customer.findbyemail", "email", email);
		
		if (list.size()>0) {
			return list.get(0);
		}
		
		return null;
	}
	
	public Customer checklogin(String email,String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Customer> list = super.findwithnamequery("Customer.checklogin", parameters);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
