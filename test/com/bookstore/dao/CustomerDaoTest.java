package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDaoTest {
	private static CustomerDao customerDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer newcustomer = new Customer();
		newcustomer.setEmail("tu123@gmail.com");
		newcustomer.setFirstname("Hoang");
		newcustomer.setLastname("Van Tu");
		newcustomer.setCity("Da Nang");
		newcustomer.setState("Da Nang");
		newcustomer.setCountry("France");
		newcustomer.setAddressLine1("123 Ha Tinh");
		newcustomer.setAddressLine2("123 Da Nang");
		newcustomer.setPassword("12345");
		newcustomer.setPhoneNumber("0977665533");
		newcustomer.setZipCode("100000");

		Customer customer = customerDao.create(newcustomer);

		assertTrue(customer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		int customerid = 10;
		Customer customer = customerDao.get(customerid);
		assertNotNull(customer);
	}

	@Test
	public void testUpdate() {
		String firstname = "Tran";
		String lastname = "Quoc Dat";
		Customer customer = customerDao.get(10);
		customer.setFirstname(firstname);
		customer.setLastname(lastname);

		Customer updatecustomer = customerDao.update(customer);

		assertTrue(updatecustomer.getFirstname().equals(firstname));
	}

	@Test
	public void testDeleteObject() {
		int customerid = 10;
		customerDao.delete(customerid);
		Customer customer = customerDao.get(customerid);

		assertNull(customer);
	}

	@Test
	public void testListAll() {
		List<Customer> list = customerDao.listAll();
		for (Customer customer : list) {
			System.out.println(customer.getLastname());
		}
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testCountAll() {
		long count = customerDao.count();
		System.err.println(count);
		assertTrue(count==7);
	}
	
	@Test
	public void testfindbyemail() {
		String email = "phap@gmail.com";
		
		Customer customer = customerDao.findbyemail(email);
		
		assertNotNull(customer);
	}
	
	@Test
	public void testchecklogin() {
		String email = "phap@gmail.com";
		String password = "12345";
		Customer customer = customerDao.checklogin(email, password);
		
		assertNotNull(customer);
	}

}
