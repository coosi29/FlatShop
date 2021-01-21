package com.coosi29.flatshop.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coosi29.flatshop.dao.UserDao;
import com.coosi29.flatshop.entity.User;
import com.coosi29.flatshop.model.UserDTO;
import com.coosi29.flatshop.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void insert(UserDTO userDTO) {
		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setAddress(userDTO.getAddress());
		user.setAvatar(userDTO.getAvatar());
		user.setFullname(userDTO.getFullname());
		user.setVerify(userDTO.isVerify());
		user.setGender(userDTO.isGender());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getRole());
		
		userDao.insert(user);
	}

	@Override
	public void update(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDTO findById(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findAll() {
		List<User> users = userDao.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User user : users) {
			UserDTO userDTO = new UserDTO(user.getEmail(), user.getPhone(), user.getPassword(), null);
			userDTO.setUserId(user.getUserId());
			userDTO.setEmail(user.getEmail());
			userDTO.setPhone(user.getPhone());
			userDTO.setAddress(user.getAddress());
			userDTO.setAvatar(user.getAvatar());
			userDTO.setFullname(user.getFullname());
			userDTO.setVerify(user.isVerify());
			userDTO.setGender(user.isGender());
			userDTO.setPassword(user.getPassword());
			userDTO.setRole(user.getRole());
			
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	@Override
	public UserDTO findByEmailOrPhoneAndPassword(String account, String password, boolean verity) {
		User user = userDao.findByEmailOrPhoneAndPassword(account, password, verity);
		UserDTO userDTO = new UserDTO(user.getEmail(), user.getPhone(), user.getPassword(), null);
		userDTO.setUserId(user.getUserId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setAddress(user.getAddress());
		userDTO.setAvatar(user.getAvatar());
		userDTO.setFullname(user.getFullname());
		userDTO.setVerify(user.isVerify());
		userDTO.setGender(user.isGender());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		return userDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		User user = userDao.loadUserByUsername(account);
		if (user == null) {
			throw new UsernameNotFoundException("Not Found!");
		}
		
//		UserPrincipal userPrincipal = new UserPrincipal(user.getEmail(), user.getPassword(), user.getEnabled(), true,
//				true, true, authorities);
		
		List<SimpleGrantedAuthority> roleList = new ArrayList<>();
		
		roleList.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		
		UserDTO userDTO = new UserDTO(user.getEmail(), user.getPhone(), user.getPassword(), roleList);
		userDTO.setUserId(user.getUserId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setAddress(user.getAddress());
		userDTO.setAvatar(user.getAvatar());
		userDTO.setFullname(user.getFullname());
		userDTO.setVerify(user.isVerify());
		userDTO.setGender(user.isGender());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		return userDTO;
	}


}
