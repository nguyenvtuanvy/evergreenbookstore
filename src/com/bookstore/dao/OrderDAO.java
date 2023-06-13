package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder order) {
		order.setOrderDate(new Date());
		order.setOrderStatus("Processing");
		
		return super.create(order);
	}

	@Override
	public BookOrder update(BookOrder order) {
		return super.update(order);
	}

	@Override
	public BookOrder get(Object orderId) {
		return super.find(BookOrder.class, orderId);
	}
	
	public BookOrder get(int orderId,int customerId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("orderId", orderId);
		parameter.put("customerId", customerId);
		
		List<BookOrder> list = super.findwithnamequery("BookOrder.findbyidandcustomer", parameter);
		
		if (!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void delete(Object orderid) {
		super.delete(BookOrder.class, orderid);	
	}

	@Override
	public List<BookOrder> listAll() {
		return super.findwithnamequery("BookOrder.findAll");
	}

	@Override
	public long count() {
		return super.countwithnamequery("BookOrder.countAll");
	}

	public List<BookOrder> listByCustomer(int customerid){
		return super.findwithnamequery("BookOrder.findbycustomer", "customerId", customerid);
	}
	
	public List<BookOrder> listMostRecentSales(){
		return super.findwithnamequery("BookOrder.findAll", 0, 3);
	}
}
