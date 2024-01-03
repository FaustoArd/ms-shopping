package com.lord.itemmanagementservice.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

public class ItemManagementDto {
	
	private String id;
	
	private String itemId;
	
	private Long productId;
	
	private int maxShare;
	
	private BigDecimal priceByMaxShare;
	
	private Map<Integer, BigDecimal> pricesByShare;
	
	private BigDecimal interest;

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

	public Map<Integer, BigDecimal> getPricesByShare() {
		return pricesByShare;
	}

	public void setPricesByShare(Map<Integer, BigDecimal> pricesByShare) {
		this.pricesByShare = pricesByShare;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	
	
}
