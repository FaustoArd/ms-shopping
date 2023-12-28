package com.lord.purchasecartservice.service;




import com.lord.purchasecartservice.dto.PurchaseCartDto;
import com.lord.purchasecartservice.model.PurchaseCart;

public interface PurchaseCartService {

	public PurchaseCart save(PurchaseCartDto purchaseCartDto);
	
	public PurchaseCart findById(Long id);
	
	
}
