package com.lord.productservice.dto;

import java.util.UUID;



public class ProductDto {
	
	private Long id;
	
	
	private String name;
	

	private UUID productIdCode;
	
	
	private Long categoryId;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UUID getProductIdCode() {
		return productIdCode;
	}
	public void setProductIdCode(UUID productIdCode) {
		this.productIdCode = productIdCode;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
