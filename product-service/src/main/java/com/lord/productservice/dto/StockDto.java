package com.lord.productservice.dto;

import java.util.UUID;

public class StockDto {
	
	private Long id;

	private int quantity;
	
	private UUID productIdCode;
	
	public int getQuantity() {
		return quantity;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public UUID getProductIdCode() {
		return productIdCode;
	}

	public void setProductIdCode(UUID productIdCode) {
		this.productIdCode = productIdCode;
	}


	
	
}
