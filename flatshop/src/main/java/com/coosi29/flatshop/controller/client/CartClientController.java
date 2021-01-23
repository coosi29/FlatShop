package com.coosi29.flatshop.controller.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.model.BillProductDTO;
import com.coosi29.flatshop.model.ProductDTO;
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

	@GetMapping(value = "/add-to-cart")
	public String cart(HttpServletRequest request, @RequestParam(name = "productId") long productId,
			HttpSession session) {
		ProductDTO productDTO = productService.findById(productId); // get product by id when user click buy product

		Object object = session.getAttribute("cart"); // get cart form session if cart is not empty

		if (object == null) { // if cart is empty => add product to cart
			BillProductDTO billProductDTO = new BillProductDTO();
			billProductDTO.setProductDTO(productDTO);
			billProductDTO.setUnitPrice(productDTO.getPrice());
			billProductDTO.setQuantity(1);
			Map<Long, BillProductDTO> map = new HashMap<Long, BillProductDTO>();
			map.put(productId, billProductDTO);
			session.setAttribute("cart", map);
		} else { // if cart is not empty
			Map<Long, BillProductDTO> map = (Map<Long, BillProductDTO>) object; 
			BillProductDTO billProductDTO = map.get(productId);
			if (billProductDTO == null) { 
				billProductDTO = new BillProductDTO();
				billProductDTO.setProductDTO(productDTO);
				billProductDTO.setUnitPrice(productDTO.getPrice());
				billProductDTO.setQuantity(1);
				map.put(productId, billProductDTO);
			} else {
				int quantity = billProductDTO.getQuantity();
				if (billProductDTO.getQuantity() < productDTO.getQuantity()) {
					billProductDTO.setQuantity(quantity);
				} else {
					billProductDTO.setQuantity(quantity + 1);
				}
			}
		}

		return "redirect:../client/cart";
	}
}
