package com.lord.itemmanagementservice.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item_management")
public class ItemManagement {
	
	@Id
	private ObjectId id;
	
	private ObjectId itemId;
	
	private Long productId;
	
	private BigDecimal itemPrice;
	
	private int maxShare;
	
	private BigDecimal priceByMaxShare;
	
	private Map<Integer, BigDecimal> pricesByShare;
	
	private BigDecimal interest;
	
	

	public ItemManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getItemId() {
		return itemId;
	}

	public void setItemId(ObjectId itemId) {
		this.itemId = itemId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
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
