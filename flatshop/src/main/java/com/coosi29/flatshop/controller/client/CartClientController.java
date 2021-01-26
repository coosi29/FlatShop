package com.coosi29.flatshop.controller.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.model.BillProductDTO;
import com.coosi29.flatshop.model.ItemsDTO;
import com.coosi29.flatshop.model.ProductDTO;
import com.coosi29.flatshop.model.UserDTO;
import com.coosi29.flatshop.model.UserPrincipal;
import com.coosi29.flatshop.service.CategoryService;
import com.coosi29.flatshop.service.ProductService;

@Controller
@RequestMapping(value = "/client")
public class CartClientController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/cart")
	public String cart(HttpServletRequest request, HttpSession session) {
		request.setAttribute("categories", categoryService.findAll());
		Object object = session.getAttribute("cart");
		int totalQuantity = 0;
		float subTotal = 0;
		float grandTotal = 0;
		if (object != null) {
			HashMap<Long, ItemsDTO> mapItem = (HashMap<Long, ItemsDTO>) object;
			for(Map.Entry<Long, ItemsDTO> entry : mapItem.entrySet()) {
			    Long key = entry.getKey();
			    ItemsDTO value = entry.getValue();
			    totalQuantity += value.getQuantity();
			    subTotal += (value.getUnitPrice() * value.getQuantity());
			}
			grandTotal = subTotal + 5;
		}
		session.setAttribute("totalQuantity", totalQuantity);
		session.setAttribute("subTotal", subTotal);
		session.setAttribute("grandTotal", grandTotal);
		return "client/cart"; 
	}
	
	// Add product to cart
	
	@GetMapping(value = "/add-to-cart")
	public String addToCart(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "productId") long productId) {
		
		ProductDTO productDTO = productService.findById(productId); // lay thong tin cua san pham khi nguoi dung chon mua
		float unitPrice = Math.round(productDTO.getPrice() - (productDTO.getPrice() * productDTO.getSaleDTO().getSalePercent() / 100));
		
		Object object = session.getAttribute("cart"); // lay danh sach san pham trong gio hang tren session
		
		if (object == null) { // neu gio hang trong => them san pham vao gio hang va tao session moi chua gio hang
			ItemsDTO itemsDTO = new ItemsDTO();
			itemsDTO.setProductDTO(productDTO);
			itemsDTO.setUnitPrice(unitPrice);
			itemsDTO.setQuantity(1);
			Map<Long, ItemsDTO> mapItem = new HashMap<Long, ItemsDTO>();
			mapItem.put(productId, itemsDTO); // luu san pham vao map(gio hang) voi key = id cua san pham
			session.setAttribute("cart", mapItem); // luu gio hang vao session
		} else {  // neu gio hang da co san pham
			Map<Long, ItemsDTO> mapItem = (Map<Long, ItemsDTO>) object;
			if (mapItem.get(productId) == null) { // neu san pham chua co trong gio hang => them san pham vao gio hang
				ItemsDTO itemsDTO = new ItemsDTO();
				itemsDTO.setProductDTO(productDTO);
				itemsDTO.setUnitPrice(unitPrice);
				itemsDTO.setQuantity(1);
				mapItem.put(productId, itemsDTO); // luu san pham vao gio hang
			} else { // neu san pham da co trong gio hang => tang so luong san pham do trong gio hang len
				ItemsDTO itemsDTO = mapItem.get(productId);
				int quantity = itemsDTO.getQuantity();
				if (quantity < 5) {
					itemsDTO.setQuantity(quantity + 1);
				}
			}
			session.setAttribute("cart", mapItem);
		}
		return "redirect:../client/cart";
	}
	
	@PostMapping(value = "/add-to-cart")
	public String addToCart(HttpSession session, @RequestParam(name = "productId") long productId,
			@RequestParam(name = "quantity") int quantity) {
		ProductDTO productDTO = productService.findById(productId);
		float unitPrice = Math.round(productDTO.getPrice() - (productDTO.getPrice() * productDTO.getSaleDTO().getSalePercent() / 100));
		
		Object object = session.getAttribute("cart");
		if (object == null) {
			ItemsDTO itemsDTO = new ItemsDTO();
			itemsDTO.setProductDTO(productDTO);
			itemsDTO.setQuantity(quantity);
			itemsDTO.setUnitPrice(unitPrice);
			Map<Long, ItemsDTO> mapItem = new HashMap<Long, ItemsDTO>();
			mapItem.put(productId, itemsDTO);
			session.setAttribute("cart", mapItem);
		} else {
			Map<Long, ItemsDTO> mapItem = (Map<Long, ItemsDTO>) object;
			if (mapItem.get(productId) == null) {
				ItemsDTO itemsDTO = new ItemsDTO();
				itemsDTO.setProductDTO(productDTO);
				itemsDTO.setQuantity(quantity);
				itemsDTO.setUnitPrice(unitPrice);
				mapItem.put(productId, itemsDTO);
			} else {
				ItemsDTO itemsDTO = mapItem.get(productId);
				itemsDTO.setQuantity(quantity);
			}
			session.setAttribute("cart", mapItem);
		}

		return "redirect:../client/cart";
	}
	
	// Detele product from cart
	
	@GetMapping(value = "/delete-from-cart")
	public String deleteFromCart(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "productId") long productId) {
		
		Object object = session.getAttribute("cart");
		Map<Long, ItemsDTO> mapItem = (Map<Long, ItemsDTO>) object;
		mapItem.remove(productId);
		session.setAttribute("cart", mapItem);
		return "redirect:../client/cart";
	}
}
