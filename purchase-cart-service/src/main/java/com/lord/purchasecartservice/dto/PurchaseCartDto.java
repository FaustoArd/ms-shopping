package com.lord.purchasecartservice.dto;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseCartDto {

	
	
	private Long id;
	
	
	private BigDecimal totalPurchasePrice;
	
	
	private List<String> items;
	

	private Long userId;
	

	private String purchaseCode;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getTotalPurchasePrice() {
		return totalPurchasePrice;
	}


	public void setTotalPurchasePrice(BigDecimal totalPurchasePrice) {
		this.totalPurchasePrice = totalPurchasePrice;
	}


	public List<String> getItems() {
		return items;
	}


	public void setItems(List<String> items) {
		this.items = items;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getPurchaseCode() {
		return purchaseCode;
	}


	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	
}
