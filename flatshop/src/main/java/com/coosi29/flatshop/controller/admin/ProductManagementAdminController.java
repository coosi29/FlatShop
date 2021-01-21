package com.coosi29.flatshop.controller.admin;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.service.CategoryService;
import com.coosi29.flatshop.service.ProductService;
import com.coosi29.flatshop.service.SaleService;

// Product Manager

@Controller
@RequestMapping(value = "/admin")
public class ProductManagementAdminController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private CategoryService categoryService;
	
	// Show all product
	@GetMapping(value = "/product-list")
	public String findAll(HttpServletRequest request) {
		int pageIndex = 0;
		int pageSize = 5;
		
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		int totalPage = 0;
		int count = productService.count();
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = count / pageSize + 1;
		}
		
		request.setAttribute("default", "default");
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("products", productService.findAll(pageIndex ,pageSize));
		return "admin/product/listProduct";
	}
	
	// Show all product by category
	
	@GetMapping(value = "/product-list-by-category")
	public String findAllByCategory(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId) {
		int pageIndex = 0;
		int pageSize = 5;
		
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		int totalPage = 0;
		int count = productService.countByCategoryId(categoryId);
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = count / pageSize + 1;
		}
		
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("products", productService.findAllByCategoryId(categoryId, pageIndex, pageSize));
		return "admin/product/listProductByCategory";
	}
	
	// Create new product
	
	@GetMapping(value = "/product-create")
	public String insert() {
		return "admin/product/createNewProduct";
	}
	
	
	// Update product
	
	@GetMapping(value = "/product-update")
	public String update(HttpServletRequest request, @RequestParam(name = "productId") long productId) {
		request.setAttribute("product", productService.findById(productId));
		request.setAttribute("sales", saleService.findAll());
		request.setAttribute("categories", categoryService.findAll());
		return "admin/product/updateProduct";
	}
}
