package com.coosi29.flatshop.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coosi29.flatshop.model.CategoryDTO;
import com.coosi29.flatshop.model.ProductDTO;
import com.coosi29.flatshop.model.SaleDTO;
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
	public String insert(HttpServletRequest request) {
		request.setAttribute("categories", categoryService.findAll());
		request.setAttribute("sales", saleService.findAll());
		return "admin/product/createNewProduct";
	}
	
	@PostMapping(value = "/product-create")
	public String insertPost(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId,
			@RequestParam(name = "productName") String productName,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "price") float price,
			@RequestParam(name = "quantity") int quantity,
			@RequestParam(name = "saleId") String saleId) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(categoryId);
		SaleDTO saleDTO = new SaleDTO();
		saleDTO.setSaleId(saleId);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setCategoryDTO(categoryDTO);
		productDTO.setSaleDTO(saleDTO);
		productDTO.setProductName(productName);
		productDTO.setDescription(description);
		productDTO.setPrice(price);
		productDTO.setQuantity(quantity);
//		if (imageFile != null && imageFile.getSize() > 0) {
//			String originalFilename = imageFile.getOriginalFilename();
//			int lastIndex = originalFilename.lastIndexOf(".");
//			String ext = originalFilename.substring(lastIndex);
//			String avatarFilename = System.currentTimeMillis() + ext;
//			File newfile = new File("C:\\image_spring_boot\\" + avatarFilename);
//			FileOutputStream fileOutputStream;
//			try {
//				fileOutputStream = new FileOutputStream(newfile);
//				fileOutputStream.write(imageFile.getBytes());
//				fileOutputStream.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			productDTO.setImage(avatarFilename);
//		}
		
		productService.insert(productDTO);
		return "redirect:../admin/product-list";
	}
	
	
	// Update product
	
	@GetMapping(value = "/product-update")
	public String update(HttpServletRequest request, @RequestParam(name = "productId") long productId) {
		request.setAttribute("product", productService.findById(productId));
		request.setAttribute("sales", saleService.findAll());
		request.setAttribute("categories", categoryService.findAll());
		return "admin/product/updateProduct";
	}
	
	// Delete Product
	
	@GetMapping(value = "/product-delete")
	public String delete(HttpServletRequest request) {
		String[] productIds = request.getParameterValues("productId");
		for (String productId : productIds) {
			productService.delete(Long.parseLong(productId));
		}
		return "redirect:../admin/product-list";
	}
}
