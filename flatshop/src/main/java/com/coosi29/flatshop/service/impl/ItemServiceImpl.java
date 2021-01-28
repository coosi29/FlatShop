package com.coosi29.flatshop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coosi29.flatshop.dao.ItemDao;
import com.coosi29.flatshop.entity.Item;
import com.coosi29.flatshop.entity.Order;
import com.coosi29.flatshop.entity.Product;
import com.coosi29.flatshop.model.ItemDTO;
import com.coosi29.flatshop.model.OrderDTO;
import com.coosi29.flatshop.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Override
	public void insert(ItemDTO itemDTO) {
		
		Product product = new Product();
		product.setProductId(itemDTO.getProductDTO().getProductId());
		Order order = new Order();
		order.setOrderId(itemDTO.getOrderDTO().getOrderId());
		
		Item item = new Item();
		item.setItemId(itemDTO.getItemId());
		item.setProduct(product);
		item.setUnitPrice(itemDTO.getUnitPrice());
		item.setQuantity(itemDTO.getQuantity());
		item.setOrder(order);
		
		itemDao.insert(item);
	}

	@Override
	public void update(ItemDTO itemDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ItemDTO> findAll(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
