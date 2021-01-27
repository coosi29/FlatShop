package com.coosi29.flatshop.controller.client;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coosi29.flatshop.dao.ItemDao;
import com.coosi29.flatshop.dao.OrderDao;
import com.coosi29.flatshop.entity.Item;
import com.coosi29.flatshop.entity.Order;
import com.coosi29.flatshop.entity.Product;
import com.coosi29.flatshop.entity.User;
import com.coosi29.flatshop.model.ItemDTO;
import com.coosi29.flatshop.model.UserPrincipal;

@Controller
@RequestMapping(value = "/client")
public class CheckoutController {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@PostMapping(value = "/checkout")
	public String checkout(HttpSession session) {
		
		float subTotal = 0;
		
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = new User();
		user.setUserId(userPrincipal.getUserId());
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		String str = "2021-01-27";
        Date date = Date.valueOf(str);
		Object object = session.getAttribute("cart");
		Map<Long, ItemDTO> mapItem = (Map<Long, ItemDTO>) object;
		List<Item> items = new ArrayList<Item>();
		
		for(Map.Entry<Long, ItemDTO> entry : mapItem.entrySet()) {
		    Long key = entry.getKey();
		    ItemDTO value = entry.getValue();
		    Product product = new Product();
		    product.setProductId(entry.getValue().getProductDTO().getProductId());
		    
		    Item item = new Item();
		    item.setItemId(entry.getValue().getItemId());
		    item.setProduct(product);
		    item.setQuantity(entry.getValue().getQuantity());
		    item.setUnitPrice(entry.getValue().getUnitPrice());
		    subTotal += (value.getUnitPrice() * value.getQuantity());
		    items.add(item);
		}
		
		Order order = new Order();
		order.setBuyDate(date);
		order.setBuyer(user);
		order.setStatus("waitting line");
		order.setItems(items);
		order.setPriceTotal(subTotal + 5);
		
		orderDao.insert(order);
		
		for(Map.Entry<Long, ItemDTO> entry : mapItem.entrySet()) {
		    Long key = entry.getKey();
		    ItemDTO value = entry.getValue();
		    
		    Product product = new Product();
		    product.setProductId(entry.getValue().getProductDTO().getProductId());
		    
		    Item item = new Item();
		    item.setItemId(entry.getValue().getItemId());
		    item.setProduct(product);
		    item.setQuantity(entry.getValue().getQuantity());
		    item.setUnitPrice(entry.getValue().getUnitPrice());
		    item.setOrder(order);
			itemDao.insert(item);
		}
	
		mapItem.clear();
		session.setAttribute("cart", mapItem);
		
		return "client/checkout";
	}
}
