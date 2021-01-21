package com.coosi29.flatshop.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coosi29.flatshop.service.CategoryService;
import com.coosi29.flatshop.service.ProductService;

@Controller
@RequestMapping(value = "/client")
public class HomeClientController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping(value = "/home")
	public String home(HttpServletRequest request) {
		request.setAttribute("hotOne", productService.hotProducts(0, 4));
		request.setAttribute("hotTwo", productService.hotProducts(1, 4));
		request.setAttribute("categories", categoryService.findAll());
		return "client/home";
	}
}
