package com.coosi29.flatshop.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coosi29.flatshop.dao.OrderDao;
import com.coosi29.flatshop.entity.Order;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insert(Order order) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(order);
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> findAll(int pageInde, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
