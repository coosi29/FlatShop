package com.coosi29.flatshop.model;

import java.sql.Date;

public class OrderDTO {

	private long orderId;
	private UserDTO userDTO;
	private Date buyDate;
	private float priceTotal;
	private String status;
	
	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderDTO(long orderId, UserDTO userDTO, Date buyDate, float priceTotal, String status) {
		super();
		this.orderId = orderId;
		this.userDTO = userDTO;
		this.buyDate = buyDate;
		this.priceTotal = priceTotal;
		this.status = status;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public float getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(float priceTotal) {
		this.priceTotal = priceTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
