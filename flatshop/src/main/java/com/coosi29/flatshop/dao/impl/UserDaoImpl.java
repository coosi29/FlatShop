package com.coosi29.flatshop.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coosi29.flatshop.dao.UserDao;
import com.coosi29.flatshop.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}

	@Override
	public User findByEmailOrPhoneAndPassword(String account, String password, boolean verity) {
		String sql = "SELECT u FROM User u WHERE (u.email = '" + account + "' or u.phone = '" + account + "') and u.password = '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return (User) query.uniqueResult();
	}

	@Override
	public User loadUserByUsername(String account) {
		String sql = "SELECT u FROM User u WHERE u.email = '" + account + "' or u.phone = '" + account + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return (User) query.uniqueResult();
	}

}
