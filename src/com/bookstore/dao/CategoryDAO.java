package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
	}

	@Override
	public Category create(Category category) {
		return super.create(category);
	}

	public Category findbyname(String categoryname) {
		List<Category> list = super.findwithnamequery("Category.findName", "name", categoryname);

		if (list != null & list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public Category update(Category category) {
		return super.update(category);
	}

	@Override
	public Category get(Object cateid) {
		return super.find(Category.class, cateid);
	}

	@Override
	public void delete(Object cateid) {
		super.delete(Category.class, cateid);
	}

	@Override
	public List<Category> listAll() {
		return super.findwithnamequery("Category.findAll");
	}

	@Override
	public long count() {
		return super.countwithnamequery("Category.countAll");
	}

}
