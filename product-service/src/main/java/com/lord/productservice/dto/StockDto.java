package com.lord.productservice.dto;

import java.util.UUID;

public class StockDto {
	
	private Long id;

	private int quantity;
	
	private Long productId;
	
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



	public Long ProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}


	
	
}
