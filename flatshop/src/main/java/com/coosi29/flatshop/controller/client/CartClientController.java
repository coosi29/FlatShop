package com.coosi29.flatshop.controller.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	@GetMapping(value = "/add-to-cart")
	public String cart(HttpServletRequest request, @RequestParam(name = "productId") long productId,
			HttpSession session) {
		List<ProductDTO> productDTOs = new ArrayList<>();
		ProductDTO productDTO = productService.findById(productId);
		productDTOs.add(productDTO);
		productDTOs.add(productDTO);
		session.setAttribute("carts", productDTOs);
		request.setAttribute("categories", categoryService.findAll());
		return "client/cart";
	}
}
