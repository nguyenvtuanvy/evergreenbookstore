package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
	}

	@Override
	public Users create(Users users) {
		return super.create(users);
	}

	@Override
	public Users update(Users users) {
		return super.update(users);
	}

	@Override
	public Users get(Object userid) {
		return super.find(Users.class, userid);
	}

	public Users findbyemail(String email) {
		List<Users> listemail = super.findwithnamequery("Users.findEmail", "email", email);
		
		if (listemail != null && listemail.size() > 0) {
			return listemail.get(0);
		}
		
		return null;
	}

	public boolean checkLogin(String email,String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Users> list = super.findwithnamequery("Users.checklogin", parameters);
		
		if (list.size() == 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public void delete(Object userid) {
		super.delete(Users.class, userid);
	}

	@Override
	public List<Users> listAll() {
		return super.findwithnamequery("Users.findAll");
	}

	@Override
	public long count() {
		return super.countwithnamequery("Users.countAll");
	}

}
