package com.coosi29.flatshop.model;

public class CartDTO {

	private int id;
	private ProductDTO productDTO;
	private int quantity;
	private float priceTotal;
	
	public CartDTO() {
		// TODO Auto-generated constructor stub
	}

	public CartDTO(int id, ProductDTO productDTO, int quantity, float priceTotal) {
		super();
		this.id = id;
		this.productDTO = productDTO;
		this.quantity = quantity;
		this.priceTotal = priceTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(float priceTotal) {
		this.priceTotal = priceTotal;
	}
	
	
}
