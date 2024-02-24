package com.lord.itemmanagementservice.dto;

import java.math.BigDecimal;

public class ItemManagementShareResponse {
	
	private String itemId;
	
	private Long productId;
	
	private int maxShare;
	
	private BigDecimal priceByMaxShare;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getMaxShare() {
		return maxShare;
	}

	public void setMaxShare(int maxShare) {
		this.maxShare = maxShare;
	}

	public BigDecimal getPriceByMaxShare() {
		return priceByMaxShare;
	}

	public void setPriceByMaxShare(BigDecimal priceByMaxShare) {
		this.priceByMaxShare = priceByMaxShare;
	}
	

}
