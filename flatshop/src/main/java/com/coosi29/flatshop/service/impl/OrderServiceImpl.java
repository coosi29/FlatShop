package com.coosi29.flatshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coosi29.flatshop.dao.OrderDao;
import com.coosi29.flatshop.entity.Item;
import com.coosi29.flatshop.entity.Order;
import com.coosi29.flatshop.entity.User;
import com.coosi29.flatshop.model.OrderDTO;
import com.coosi29.flatshop.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public void insert(OrderDTO orderDTO) {

		Item item = new Item();
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		
		
		User user = new User();
		
		
		Order order = new Order();
		order.setOrderId(orderDTO.getOrderId());
		order.setBuyDate(orderDTO.getBuyDate());
		order.setStatus(order.getStatus());
		order.setPriceTotal(orderDTO.getPriceTotal());
		order.setItems(items);
		order.setBuyer(user);
		
		orderDao.insert(order);
	}

	@Override
	public void update(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderDTO> findAll(int pageInde, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
