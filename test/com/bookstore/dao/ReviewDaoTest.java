package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.ReviewTable;

public class ReviewDaoTest {
	private static ReviewDao reviewDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReviewTable() {
		ReviewTable review = new ReviewTable();
		
		Book book = new Book();
		book.setBookId(8);
		Customer customer = new Customer();
		customer.setCustomerId(9);
		
		review.setBook(book);
		review.setCustomer(customer);
		review.setHeadline("book very good!");
		review.setRating(5);
		review.setComment("I have just read this book.Very good.");
		
		
		ReviewTable createreview = reviewDao.create(review);
		assertTrue(createreview.getReviewId() > 0);
	}

	@Test
	public void testUpdateReviewTable() {
		int reviewid = 1;
		ReviewTable review = reviewDao.get(reviewid);
		review.setHeadline("excellent book");
		
		ReviewTable updatereview = reviewDao.update(review);
		
		assertEquals(review.getHeadline(), updatereview.getHeadline());
	}

	@Test
	public void testGet() {
		int reviewid = 1;
		ReviewTable review = reviewDao.get(reviewid);
		
		assertNotNull(review);
	}

	@Test
	public void testDeleteObject() {
		int reviewid = 3;
		reviewDao.delete(reviewid);
		
		ReviewTable review = reviewDao.get(reviewid);
		
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<ReviewTable> list = reviewDao.listAll();
		for (ReviewTable reviewTable : list) {
			System.out.println(reviewTable.getHeadline()+" "+reviewTable.getBook().getTitle()+" "+reviewTable.getCustomer().getFirstname());
		}
		assertTrue(list.size()>0);
	}

	@Test
	public void testCount() {
		long total = reviewDao.count();
		
		assertTrue(total == 2);
	}
	
	@Test
	public void testfindbycutomerandbookfail() {
		Integer customerid = 100;
		Integer bookid = 99;
		
		ReviewTable review = reviewDao.findByCutomerAndBook(customerid, bookid);
		
		assertNull(review);
	}
	
	@Test
	public void testfindbycutomerandbookSuccesfully() {
		Integer customerid = 4;
		Integer bookid = 4;
		
		ReviewTable review = reviewDao.findByCutomerAndBook(customerid, bookid);
		
		assertNotNull(review);
	}

	@Test
	public void testlistMostRecnt() {
		List<ReviewTable> list = reviewDao.listMostRecent();
		
		assertEquals(3, list.size());
	}
	
}
