package com.lord.purchasecartservice.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.lord.purchasecartservice.dto.PurchaseCartDto;
import com.lord.purchasecartservice.model.PurchaseCart;

@Component
public class PurchaseCartMapper {

	public PurchaseCart toPurchaseCart(PurchaseCartDto purchaseCartDto) {
		if (purchaseCartDto == null) {
			return null;
		}
		PurchaseCart purchaseCart = new PurchaseCart();
		purchaseCart.setId(purchaseCartDto.getId());
		purchaseCart.setItems(purchaseCartDto.getItems());
		purchaseCart.setTotalPurchasePrice(purchaseCartDto.getTotalPurchasePrice());
		purchaseCart.setPurchaseCode(purchaseCartDto.getPurchaseCode());
		purchaseCart.setUserId(purchaseCartDto.getUserId());

		return purchaseCart;

	}

	public PurchaseCartDto toPurchaseCartDto(PurchaseCart purchaseCart) {
		if (purchaseCart == null) {
			return null;
		}
		PurchaseCartDto purchaseCartDto = new PurchaseCartDto();
		purchaseCartDto.setId(purchaseCart.getId());
		purchaseCartDto.setItems(purchaseCart.getItems());
		purchaseCartDto.setTotalPurchasePrice(purchaseCart.getTotalPurchasePrice());
		purchaseCartDto.setPurchaseCode(purchaseCart.getPurchaseCode());
		purchaseCartDto.setUserId(purchaseCart.getUserId());
		return purchaseCartDto;
	}

	public List<PurchaseCartDto> toPurchasesCartDto(List<PurchaseCart> purchasesCart) {
		if (purchasesCart == null) {
			return null;
		}
		List<PurchaseCartDto> purchases = purchasesCart.stream().map(this::toPurchaseCartDto)
				.collect(Collectors.toList());
		return purchases;
	}
}
