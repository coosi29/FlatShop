package com.coosi29.flatshop.controller.authen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coosi29.flatshop.model.UserDTO;
import com.coosi29.flatshop.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailSender mailSender;
	
	
	@GetMapping(value = "/register")
	public String register() {
		return "authen/register";
	}
	
	@PostMapping(value = "/register")
	public String register(HttpServletRequest request,
			@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		userDTO.setPassword(new BCryptPasswordEncoder().encode(password));
		userService.insert(userDTO);
		
		sendEmail("truonganvu2000@gmail.com", "coosi29@gmail.com", "Hello", "Xin Chao, Xac nhan de co the dang nhap tren FlatShop!");
		return "authen/login";
	}
	
	public void sendEmail(String from, String to, String subject, String content) {
		SimpleMailMessage mailMessage =  new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);
		
		mailSender.send(mailMessage);
	}
}
