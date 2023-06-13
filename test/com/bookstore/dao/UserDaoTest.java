package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDaoTest{
	private static UserDAO userdao;

	@BeforeClass
	public static void setupclass() {
		userdao = new UserDAO();
	}

	@Test
	public void testCreateUsers() {
		Users users1 = new Users("hieu.com", "123456", "nguyenvanhieu");

		users1 = userdao.create(users1);

		assertTrue(users1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersfail() {
		Users users1 = new Users();

		users1 = userdao.create(users1);

		assertTrue(users1.getUserId() > 0);
	}

	@Test
	public void TestUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("tuanvy.com");
		user.setPassword("12345");
		user.setFullName("nguyenvtuanvy");

		user = userdao.update(user);
		String expected = "12345";
		String actual = user.getPassword();

		assertEquals(expected, actual);
	}

	@Test
	public void testgetusersfound() {
		Users users = userdao.get(1);
		System.out.println(users.getEmail());
		assertNotNull(users);
	}

	@Test
	public void testgetusersnotfound() {
		Users users = userdao.get(99);

		assertNull(users);
	}

	@Test
	public void testdeleteuser() {
		userdao.delete(8);

		assertNull(userdao.get(8));
	}

	@Test(expected = EntityNotFoundException.class)
	public void testdeleteusernonexituser() {
		userdao.delete(8);
	}

	@Test
	public void teslistall() {
		List<Users> list = userdao.listAll();
		for(Users u : list) {
			System.out.println(u.getEmail());
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testcount() {
		long totalusers = userdao.count();
		
		assertEquals(3, totalusers);
	}
	
	@Test
	public void testfindbyemail() {
		String email = "hieu.com";
		Users users = userdao.findbyemail(email);
		
		assertNotNull(users);
	}
	
	@Test
	public void testcheckloginSuccess() {
		String email = "minh@.com";
		String pass = "12345";
		boolean result = userdao.checkLogin(email, pass);
		
		assertTrue(result);
	}
	
	@Test
	public void testcheckloginFail() {
		String email = "hieu@.com";
		String pass = "12345";
		boolean result = userdao.checkLogin(email, pass);
		
		assertFalse(result);
	}
	
	
	@AfterClass
	public static void tearDownClass() {
		userdao.close();
	}

}
