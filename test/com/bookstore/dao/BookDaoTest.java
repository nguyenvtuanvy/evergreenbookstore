package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDaoTest {
	private static BookDao bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newbook = new Book();

		Category category = new Category("Java core");
		category.setCategoryId(1);
		newbook.setCategory(category);

		newbook.setTitle("Effective Java (2nd Edition)");
		newbook.setAuthor("Joshua Bloch");
		newbook.setDepcription("New coverage of generics, enums, annotations, autoboxing");
		newbook.setPrice(38.87f);
		newbook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publistdate = dateFormat.parse("03/22/2023");
		newbook.setPublishDate(publistdate);

		String imagePath = "D:\\tailieu\\books\\Effective Java.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		newbook.setImage(imageByte);

		Book createBook = bookDao.create(newbook);

		assertTrue(createBook.getBookId() > 0);
	}

	@Test
	public void testCreate2ndBook() throws ParseException, IOException {
		Book newbook = new Book();

		Category category = new Category("Java core");
		category.setCategoryId(1);
		newbook.setCategory(category);

		newbook.setTitle("Spring in Action");
		newbook.setAuthor("Craig Walls");
		newbook.setDepcription("Spring in Action, Fourth Edition is a hands-on guide to the Spring Framework");
		newbook.setPrice(33.99f);
		newbook.setIsbn("161729120X");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publistdate = dateFormat.parse("03/22/2023");
		newbook.setPublishDate(publistdate);

		String imagePath = "D:\\tailieu\\books\\Spring in Action.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		newbook.setImage(imageByte);

		Book createBook = bookDao.create(newbook);

		assertTrue(createBook.getBookId() > 0);
	}

	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existbook = new Book();
		existbook.setBookId(2);

		Category category = new Category("Python Core");
		category.setCategoryId(2);
		existbook.setCategory(category);

		existbook.setTitle("Effective Java (3rd Edition)");
		existbook.setAuthor("Joshua Bloch");
		existbook.setDepcription("New coverage of generics, enums, annotations, autoboxing");
		existbook.setPrice(40f);
		existbook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publistdate = dateFormat.parse("03/22/2023");
		existbook.setPublishDate(publistdate);

		String imagePath = "D:\\tailieu\\books\\Effective Java.jpg";
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath));
		existbook.setImage(imageByte);

		Book updateBook = bookDao.update(existbook);

		assertEquals(updateBook.getAuthor(), "Joshua Bloch");
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookid = 100;
		bookDao.delete(bookid);

		assertTrue(true);
	}

	@Test
	public void testDeleteBookSusscess() {
		Integer bookid = 16;
		bookDao.delete(bookid);
		Book book = bookDao.get(bookid);
		assertNull(book);
	}

	@Test
	public void testGetBookFail() {
		Integer bookid = 99;
		Book book = bookDao.get(bookid);

		assertNull(book);
	}

	@Test
	public void testGetBookSusscess() {
		Integer bookid = 3;
		Book book = bookDao.get(bookid);
		System.out.println(book.getTitle());
		assertNotNull(book);
	}

	@Test
	public void testListBook() {
		List<Book> list = bookDao.listAll();

		for (Book book : list) {
			System.out.println(book.getTitle() + " - " + book.getAuthor());
		}

		assertFalse(list.isEmpty());
	}

	@Test
	public void testfindbytitle() {
		String title = "Spring in Action";
		Book book = bookDao.findByTitle(title);

		System.out.println(book.getBookId() + " - " + book.getAuthor() + " - " + book.getPrice());

		assertNotNull(book);
	}

	@Test
	public void testcountbook() {
		long count = bookDao.count();
		System.out.println(count);
		assertEquals(2, count);
	}

	@Test
	public void testcountbookbycategory() {
		int categoryid = 1;

		long result = bookDao.countbycategory(categoryid);

		assertTrue(result == 6);
	}

	@Test
	public void testlistbycategory() {
		int categoryid = 8;

		List<Book> list = bookDao.listByCategory(categoryid);

		for (Book book : list) {
			System.out.println(book.getTitle());
		}
		assertTrue(list.size() > 0);
	}

	@Test
	public void testlistnewbook() {
		List<Book> list = bookDao.listnewbook();
		for (Book book : list) {
			System.out.println(book.getTitle() + "  " + book.getPublishDate());
		}
		assertEquals(list.size(), 4);
	}

	@Test
	public void testlistsearchintitle() {
		String keyword = "java";
		List<Book> list = bookDao.listsearch(keyword);
		for (Book book : list) {
			System.out.println(book.getTitle());
		}
		assertEquals(list.size(), 4);
	}

	@Test
	public void testlistsearchinauthor() {
		String keyword = "Craig Walls";
		List<Book> list = bookDao.listsearch(keyword);
		for (Book book : list) {
			System.out.println(book.getTitle());
		}
		assertEquals(list.size(), 1);
	}

	@Test
	public void testlistsearchindecip() {
		String keyword = "You'll move between";
		List<Book> list = bookDao.listsearch(keyword);
		for (Book book : list) {
			System.out.println(book.getTitle());
		}
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testlistsellingbooks() {
		List<Book> list = bookDao.listBestSellingBooks();
		
		for (Book book : list) {
			System.out.println(book.getTitle());
		}
		assertEquals(4, list.size());
	}
	
	@Test
	public void testlistmostfavoredbooks() {
		List<Book> list = bookDao.listMostFavoredBooks();
		
		for (Book book : list) {
			System.out.println(book.getTitle());
		}
		
		assertEquals(4, list.size());
	}
}
