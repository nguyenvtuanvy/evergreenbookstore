package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.entity.OrderDetailId;

public class OrderDAOTest {

	private static OrderDAO orderDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}

	@Test
	public void testCreateBookOrder() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(1);

		order.setCustomer(customer);
		order.setFirstname("Tuan");
		order.setLastname("Vy");
		order.setPhone("0327443323");
		order.setAddressLine1("123 DK2, Dien Phuong,Dien Ban,Quang Nam");
		order.setAddressLine2("123 Tran Cao Van,Thanh Khe,Da Nang");
		order.setCity("Da Nang");
		order.setState("Da Nang");
		order.setPaymentMethod("Paypal");
		order.setZipcode("1001001");
		order.setCountry("VN");

		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();

		Book book = new Book(9);

		orderDetail.setBook(book);
		orderDetail.setQuantity(3);
		orderDetail.setSubtotal(110.16f);
		orderDetail.setBookOrder(order);

		orderDetails.add(orderDetail);

		order.setOrderDetails(orderDetails);
		order.setTax(6.8f);
		order.setShippingFee(2.0f);
		order.setSubtotal(110.16f);
		order.setOrderTotal(118.96f);

		BookOrder bookOrder = orderDAO.create(order);

		assertTrue(bookOrder.getOrderId() > 0);
	}

	@Test
	public void testCreateBookOrder2() {
		BookOrder order = new BookOrder();
		Customer customer = new Customer();
		customer.setCustomerId(8);

		order.setCustomer(customer);
		order.setFirstname("Tao Ten Vy");
		order.setPhone("0327443323");
		order.setAddressLine1("123 DK2, Dien Phuong,Dien Ban,Quang Nam");

		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail1 = new OrderDetail();

		Book book1 = new Book(5);
		orderDetail1.setBook(book1);
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(60.5f);
		orderDetail1.setBookOrder(order);

		orderDetails.add(orderDetail1);

		Book book2 = new Book(8);
		OrderDetail orderDetail2 = new OrderDetail();
		orderDetail2.setBook(book2);
		orderDetail2.setQuantity(1);
		orderDetail2.setSubtotal(38.2f);
		orderDetail2.setBookOrder(order);

		orderDetails.add(orderDetail2);

		order.setOrderDetails(orderDetails);

		orderDAO.create(order);

		assertTrue(order.getOrderId() > 0 && order.getOrderDetails().size() == 2);
	}

	@Test
	public void testUpdateBookOrderShippingAddress() {
		int orderid = 16;
		BookOrder order = orderDAO.get(orderid);
		order.setAddressLine1("New Shipping Address");
		orderDAO.update(order);

		BookOrder updateorder = orderDAO.get(orderid);

		assertEquals(order.getAddressLine1(), updateorder.getAddressLine1());
	}

	@Test
	public void testUpdateBookOrderDetail() {
		int orderid = 17;
		BookOrder order = orderDAO.get(orderid);

		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();

		while (iterator.hasNext()) {
			OrderDetail orderDetail = (OrderDetail) iterator.next();
			if (orderDetail.getBook().getBookId() == 8) {
				orderDetail.setQuantity(5);
				orderDetail.setSubtotal(191);
			}
		}

		orderDAO.update(order);


		iterator = order.getOrderDetails().iterator();

		int expectedQuantity = 5;
		float expectedSubtotal = 191;
		int actualQuantity = 0;
		float actualSubtotal = 0;
		while (iterator.hasNext()) {
			OrderDetail orderDetail = (OrderDetail) iterator.next();
			if (orderDetail.getBook().getBookId() == 8) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
		}

		assertEquals(expectedQuantity,actualQuantity);
		
		assertEquals(expectedSubtotal,actualSubtotal, 0.0f);
	}

	@Test
	public void testGet() {
		int orderid = 28;
		BookOrder bookOrder = orderDAO.get(orderid);
		System.out.println(bookOrder.getFirstname());
		System.out.println(bookOrder.getLastname());
		System.out.println(bookOrder.getAddressLine1());
		System.out.println(bookOrder.getAddressLine2());
		System.out.println(bookOrder.getPhone());
		System.out.println(bookOrder.getCountry());
		System.out.println(bookOrder.getCity());
		System.out.println(bookOrder.getState());
		System.out.println(bookOrder.getZipcode());
		System.out.println(bookOrder.getPaymentMethod());
		System.out.println(bookOrder.getShippingFee());
		System.out.println(bookOrder.getSubtotal());
		System.out.println(bookOrder.getTax());
		System.out.println(bookOrder.getOrderTotal());
		assertEquals(1, bookOrder.getOrderDetails().size());
	}

	@Test
	public void testDeleteObject() {
		int orderid = 27;
		orderDAO.delete(orderid);
		
		BookOrder order = orderDAO.get(orderid);
		
		assertNull(order);
	}

	@Test
	public void testListAll() {
		List<BookOrder> list = orderDAO.listAll();

		for (BookOrder order : list) {
			System.out.println(order.getCustomer().getFirstname());
			for (OrderDetail deatail : order.getOrderDetails()) {
				Book book = deatail.getBook();
				int quantity = deatail.getQuantity();
				float subtotal = deatail.getSubtotal();
				System.out.println("\t" + book.getTitle() + " - " + quantity + " - " + subtotal);
			}
		}

		assertTrue(list.size() > 0);
	}

	@Test
	public void testCount() {
		long result = orderDAO.count();
		
		assertEquals(3, result);
	}
	
	@Test
	public void testlistfindbycustomer() {
		int customerid = 8;
		
		List<BookOrder> list = orderDAO.listByCustomer(customerid);
		
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testlistfindbyidandcustomer() {
		int orderid = 17;
		int customerid = 7;
		
		BookOrder bookOrder = orderDAO.get(orderid, customerid);
		
		assertNotNull(bookOrder);
	}

	@Test
	public void testlistMostRecentSale() {
		List<BookOrder> list = orderDAO.listMostRecentSales();
		
		assertEquals(3, list.size());
	}
	
}
