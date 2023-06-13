package com.bookstore.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDao extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDao() {
	}

	@Override
	public Book create(Book book) {
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}

	@Override
	public Book update(Book book) {
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object bookid) {
		return super.find(Book.class, bookid);
	}

	@Override
	public void delete(Object bookid) {
		super.delete(Book.class, bookid);
	}

	@Override
	public List<Book> listAll() {
		return super.findwithnamequery("Book.findAll");
	}

	public Book findByTitle(String title) {
		List<Book> list = super.findwithnamequery("Book.findByTitle", "title", title);

		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public List<Book> listByCategory(int categoryid){
		return super.findwithnamequery("Book.findbyCategory", "catId", categoryid);
	}
	
	public List<Book> listnewbook(){
		return super.findwithnamequery("Book.listnewbook", 0, 4);
	}
	
	public List<Book> listsearch(String keyword){
		return super.findwithnamequery("Book.search", "keyword", keyword);
	}
	
	@Override
	public long count() {
		return super.countwithnamequery("Book.countAll");
	}

	public long countbycategory(int categoryid) {
		return super.countwithnamequery("Book.countbycategory", "cateId", categoryid);
	}
	
	public List<Book> listBestSellingBooks(){
		return super.findwithnamequery("OrderDetail.bestSelling", 0, 4);
	}
	
	public List<Book> listMostFavoredBooks(){
		List<Book> mostFavoredBooks = new ArrayList<>();
		List<Object[]> result = super.findwithnamequeryObject("Review.mostFavoredBooks", 0, 4);
		
		if (!result.isEmpty()) {
			for (Object[] element : result) {
				Book book = (Book) element[0];
				mostFavoredBooks.add(book);
			}
		}
		
		return mostFavoredBooks;
	}
}
