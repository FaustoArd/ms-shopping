package com.lord.stockservice.dto;



public class StockDto {
	
	private Long id;
	
	
	private int quantity;
	
	private String productIdCode;
	
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
	
	public String getProductIdCode() {
		return productIdCode;
	}

	public void setProductIdCode(String productIdCode) {
		this.productIdCode = productIdCode;
	}



	
}