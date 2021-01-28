package com.coosi29.flatshop.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.dao.RoleDao;
import com.coosi29.flatshop.service.RoleService;
import com.coosi29.flatshop.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserManagementAdminController {

	// User Manager

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	// Show all users
	
	@GetMapping("/user-list")
	public String userList(HttpServletRequest request) {
		int pageIndex = 0;
		int pageSize = 5;
		int totalPage = 0;
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		
		int count = userService.count();
		if (count % 5 == 0 ) {
			totalPage = count / 5;
		} else {
			totalPage = count / 5 + 1;
		}
		
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("users", userService.findAll(pageIndex, pageSize));
		return "admin/user/listUser";
	}
	
	// Create new account
	
	@GetMapping(value = "/user-create")
	public String userCreate(HttpServletRequest request) {
		request.setAttribute("roles", roleService.findAll());
		return "admin/user/createUser";
	}
	
	// Update user
	
	@GetMapping(value = "user-update")
	public String userUpdate(HttpServletRequest request, @RequestParam(name = "userId") long userId) {
		request.setAttribute("roles", roleService.findAll());
		request.setAttribute("user", userService.findById(userId));
		return "admin/user/updateUser";
	}
	
	
	// Delete user
	
	@GetMapping(value = "/user-delete")
	public String userDelete(HttpServletRequest request) {
		String[] userIds = request.getParameterValues("userId");
		for (String userId : userIds) {
			userService.delete(Long.parseLong(userId));
		}
		return "redirect:/admin/user-list";
	}
}
