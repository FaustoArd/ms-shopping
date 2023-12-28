package com.lord.purchasecartservice.model;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="purchase_cart")
public class PurchaseCart {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	
	@Column(name="total_purchase_price")
	private BigDecimal totalPurchasePrice;
	
	@Column(name="items_id")
	private List<String> itemsId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="purchase_code")
	private String purchaseCode;
	
	public PurchaseCart() {
		super();
	}
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

	public List<String> getItemsId() {
		return itemsId;
	}

	public void setItemsId(List<String> itemsId) {
		this.itemsId = itemsId;
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
