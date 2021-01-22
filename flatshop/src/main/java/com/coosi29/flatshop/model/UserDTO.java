package com.coosi29.flatshop.model;

import com.coosi29.flatshop.entity.Role;

public class UserDTO {

	private long userId;
	private String email;
	private String password;
	private String fullname;
	private String phone;
	private String address;
	private boolean gender;
	private boolean verify;
	private Role role;
	private String avatar;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public UserDTO(long userId, String email, String password, String fullname, String phone, String address,
			boolean gender, boolean verify, Role role, String avatar) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.verify = verify;
		this.role = role;
		this.avatar = avatar;
	}



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
