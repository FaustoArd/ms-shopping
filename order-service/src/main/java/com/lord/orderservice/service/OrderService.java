package com.lord.orderservice.service;

import java.math.BigDecimal;
import java.util.List;

import com.lord.orderservice.dto.ItemDto;
import com.lord.orderservice.dto.OrderDto;

public interface OrderService {
	
	public OrderDto placeOrder(OrderDto orderDto);
	
	public BigDecimal updateOrder(OrderDto orderDto);
	
	public List<OrderDto> findAllOrders(Long purchaseCartId);
	
	public String deleteOrder(String orderId);
	
	public BigDecimal getOrderTotalPrice(BigDecimal itemPrice,int quantity);
	
	public BigDecimal getItemPrice(String itemId);
	
	public boolean isInStock(String itemId,int quantity);
	
	public ItemDto getItem(String itemId);

}
