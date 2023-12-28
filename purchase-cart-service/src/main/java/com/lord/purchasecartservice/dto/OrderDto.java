package com.lord.purchasecartservice.dto;

import java.math.BigDecimal;

public class OrderDto {

	
	private String id;
	
	private String itemId;
	
	private BigDecimal itemPrice;
	
	private BigDecimal orderTotalPrice;
	
	private Long purchaseCart;
	
	private int quantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Long getPurchaseCart() {
		return purchaseCart;
	}

	public void setPurchaseCart(Long purchaseCart) {
		this.purchaseCart = purchaseCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
