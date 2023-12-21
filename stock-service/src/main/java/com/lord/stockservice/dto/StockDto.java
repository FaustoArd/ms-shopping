package com.lord.stockservice.dto;

import jakarta.persistence.Column;


public class StockDto {
	
	private Long id;
	
	
	private int quantity;
	

	private boolean available;
	
	private String productIdCode;
	


	public String getProductIdCode() {
		return productIdCode;
	}

	public void setProductIdCode(String productIdCode) {
		this.productIdCode = productIdCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
