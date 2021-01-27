package com.coosi29.flatshop.dao;

import java.util.List;

import com.coosi29.flatshop.entity.Order;

public interface OrderDao {

	void insert(Order order);
	
	void update(Order order);
	
	void delete(long orderId);
	
	List<Order> findAll(int pageInde, int pageSize);
	
}
