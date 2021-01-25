package com.coosi29.flatshop.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String cart(HttpServletRequest request) {
		request.setAttribute("categories", categoryService.findAll());
		return "client/cart"; 
	}

//	@GetMapping(value = "/add-to-cart")
//	public String cart(HttpServletRequest request, @RequestParam(name = "productId") long productId,
//			HttpSession session) {
//		ProductDTO productDTO = productService.findById(productId); // get product by id when user click buy product
//
//		Object object = session.getAttribute("cart"); // get cart form session if cart is not empty
//
//		if (object == null) { // if cart is empty => add product to cart
//			BillProductDTO billProductDTO = new BillProductDTO();
//			billProductDTO.setProductDTO(productDTO);
//			billProductDTO.setUnitPrice(productDTO.getPrice());
//			billProductDTO.setQuantity(1);
//			Map<Long, BillProductDTO> map = new HashMap<Long, BillProductDTO>();
//			map.put(productId, billProductDTO);
//			session.setAttribute("cart", map);
//		} else { // if cart is not empty
//			Map<Long, BillProductDTO> map = (Map<Long, BillProductDTO>) object; 
//			BillProductDTO billProductDTO = map.get(productId);
//			if (billProductDTO == null) { 
//				billProductDTO = new BillProductDTO();
//				billProductDTO.setProductDTO(productDTO);
//				billProductDTO.setUnitPrice(productDTO.getPrice());
//				billProductDTO.setQuantity(1);
//				map.put(productId, billProductDTO);
//			} else {
//				int quantity = billProductDTO.getQuantity();
//				if (billProductDTO.getQuantity() < productDTO.getQuantity()) {
//					billProductDTO.setQuantity(quantity);
//				} else {
//					billProductDTO.setQuantity(quantity + 1);
//				}
//			}
//		}
//
//		return "redirect:../client/cart";
//	}
	
	@GetMapping(value = "/add-to-cart")
	public String cart(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "productId") long productId) {
		
		ProductDTO productDTO = productService.findById(productId); // lay thong tin cua san pham khi nguoi dung chon mua
		
		Object object = session.getAttribute("cart"); // lay danh sach san pham trong gio hang tren session
		
		if (object == null) { // neu gio hang trong => them san pham vao gio hang va tao session moi chua gio hang
			ItemsDTO itemsDTO = new ItemsDTO();
			itemsDTO.setProductDTO(productDTO);
			itemsDTO.setUnitPrice(productDTO.getPrice());
			itemsDTO.setQuantity(1);
			Map<Long, ItemsDTO> mapItem = new HashMap<Long, ItemsDTO>();
			mapItem.put(productId, itemsDTO); // luu san pham vao map(gio hang) voi key = id cua san pham
			session.setAttribute("cart", mapItem); // luu gio hang vao session
		} else {  // neu gio hang da co san pham
			Map<Long, ItemsDTO> mapItem = (Map<Long, ItemsDTO>) object;
			if (mapItem.get(productId) == null) { // neu san pham chua co trong gio hang => them san pham vao gio hang
				ItemsDTO itemsDTO = new ItemsDTO();
				itemsDTO.setProductDTO(productDTO);
				itemsDTO.setUnitPrice(productDTO.getPrice());
				itemsDTO.setQuantity(1);
				mapItem.put(productId, itemsDTO); // luu san pham vao gio hang
			} else { // neu san pham da co trong gio hang => tang so luong san pham do trong gio hang len
				ItemsDTO itemsDTO = mapItem.get(productId);
				int quantity = itemsDTO.getQuantity();
				itemsDTO.setQuantity(quantity + 1);
			}
			session.setAttribute("cart", mapItem);
		}
		return "redirect:../client/cart";
	}
}
