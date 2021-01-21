package com.coosi29.flatshop.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.service.CategoryService;
import com.coosi29.flatshop.service.ProductService;

@Controller
@RequestMapping(value = "/client")
public class ProductDetailsClientController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping(value = "/product-details")
	public String productDetails(HttpServletRequest request, @RequestParam(name = "productId") long productId) {
		request.setAttribute("categories", categoryService.findAll());
		request.setAttribute("product", productService.findById(productId));
		return "client/product_details";
	}
}
