package com.coosi29.flatshop.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.service.ProductService;

@Controller
@RequestMapping(value = "/client")
public class SearchProductClientController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/search")
	public String search(HttpServletRequest request,
			@RequestParam(name = "pricing", required = false) String pricing,
			@RequestParam(name = "sort", required = false) String sort,
			@RequestParam(name = "text", required = false) String text) {
		int pageIndex = 0;
		int pageSize = 6;
		int totalPage = 3;
		long categoryId = 1;
		if(request.getParameter("categoryId") != null) {
			categoryId = Long.parseLong(request.getParameter("categoryId"));
		}
		
		float priceFrom = 0;
		float priceTo = 0;
		if (pricing != null) {
			if (pricing.equals("under50")) {
				priceTo = 50;
			} else if(pricing.equals("50to70")) {
				priceFrom = 50;
				priceTo = 70;
			} else if(pricing.equals("greaterthan70")) {
				priceFrom = 70;
				priceTo = 1000;
			}
		} else {
			pricing = "default";
		}
		
		request.setAttribute("text", text);
		request.setAttribute("sort", sort);
		request.setAttribute("pricing", pricing);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("products", productService.search(categoryId, pricing, priceFrom, priceTo, sort, text, pageIndex, pageSize));
		return "client/product_grid";
	}
}
