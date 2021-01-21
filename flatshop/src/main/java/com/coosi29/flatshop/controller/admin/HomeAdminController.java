package com.coosi29.flatshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class HomeAdminController {

	@GetMapping(value = "/home")
	public String home() {
		return "admin/home";
	}
	
	
}
