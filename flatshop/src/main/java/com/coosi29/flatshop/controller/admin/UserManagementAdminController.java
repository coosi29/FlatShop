package com.coosi29.flatshop.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coosi29.flatshop.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserManagementAdminController {

	// User Manager

	@Autowired
	private UserService userService;
	
	@GetMapping("/user-list")
	public String userList(HttpServletRequest request) {
		request.setAttribute("users", userService.findAll());
		return "admin/user/listUser";
	}
}
