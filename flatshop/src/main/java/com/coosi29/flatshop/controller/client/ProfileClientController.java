package com.coosi29.flatshop.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.entity.User;
import com.coosi29.flatshop.model.RoleDTO;
import com.coosi29.flatshop.model.UserDTO;
import com.coosi29.flatshop.model.UserPrincipal;
import com.coosi29.flatshop.service.CategoryService;
import com.coosi29.flatshop.service.UserService;
import com.mysql.cj.Session;

@Controller
@RequestMapping(value = "/client")
public class ProfileClientController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/profile")
	public String profile(HttpServletRequest request) {
		request.setAttribute("categories", categoryService.findAll());
		return "client/profile";
	}
	
	@GetMapping(value = "/profile-from-cart")
	public String profileFromCart(HttpServletRequest request) {
		request.setAttribute("categories", categoryService.findAll());
		request.setAttribute("messageError", "Please update your delivery information.");
		return "client/profile";
	}
	
	@PostMapping(value = "/profile-update")
	public String profileUpdate(HttpServletRequest request,
			@RequestParam(name = "fullname", required = false) String fullname,
			@RequestParam(name = "phone") String phone,
			@RequestParam(name = "address") String address) {
		UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
		userPrincipal.setFullname(fullname);
		userPrincipal.setPhone(phone);
		userPrincipal.setAddress(address);
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleId(userPrincipal.getRole().getRoleId());
		roleDTO.setRoleName(userPrincipal.getRole().getRoleName());
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userPrincipal.getUserId());
		userDTO.setEmail(userPrincipal.getEmail());
		userDTO.setPhone(userPrincipal.getPhone());
		userDTO.setAddress(userPrincipal.getAddress());
		userDTO.setAvatar(userPrincipal.getAvatar());
		userDTO.setFullname(userPrincipal.getFullname());
		userDTO.setVerify(userPrincipal.isVerify());
		userDTO.setGender(userPrincipal.isGender());
		userDTO.setPassword(userPrincipal.getPassword());
		userDTO.setRoleDTO(roleDTO);
		
		userService.update(userDTO);
		request.setAttribute("messageSuccess", "Update delivery information successful.");
		request.setAttribute("categories", categoryService.findAll());
		return "client/profile";
	}
}
