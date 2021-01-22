package com.coosi29.flatshop.controller.authen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.model.UserDTO;
import com.coosi29.flatshop.model.UserPrincipal;
import com.coosi29.flatshop.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, @RequestParam(name = "err", required = false) String err) {
		request.setAttribute("err", err);
		return "authen/login";
	}
	
	@GetMapping(value = "/home")
	public String loginSuccess(HttpServletRequest request, HttpSession session) {
		UserPrincipal userPrincipal =  (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		session.setAttribute("user", userPrincipal);
		String username = userPrincipal.getEmail();
		session.setAttribute("username", username.split("@")[0]);
		if (request.isUserInRole("ADMIN")) {
			return "redirect:/admin/home";
		} else {
			return "redirect:/client/home";
		}
	}
	
	@GetMapping(value = "/logout")
	public String logout() {
		return "redirect:/login";
	}
	
//	@PostMapping(value = "/login")
//	public String login (HttpSession session, @RequestParam(name = "account") String account,
//			@RequestParam(name = "password") String password, HttpServletRequest request) {
//		try {
//			UserDTO userDTO = userService.findByEmailOrPhoneAndPassword(account, password, true);
//			String username = account.split("@")[0];
//			session.setAttribute("username", username);
//			session.setAttribute("user", userDTO);
//			return "redirect:/client/home";
//		} catch (Exception e) {
//			request.setAttribute("err", "Looks like either your account. Wanna try again?");
//			return "authen/login";
//		}
//	}
}
