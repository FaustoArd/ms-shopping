package com.lord.orderservice.mapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.lord.orderservice.dto.ItemDto;
import com.lord.orderservice.dto.OrderDto;
import com.lord.orderservice.model.Order;


@Component
public class OrderMapper {
	
	//OrderDto to Order
	public Order toOrder(OrderDto orderDto) {
		if(orderDto==null){
			return null;
		}
		Order order= new Order();
		if (orderDto.getId() != null) {
			order.setId(new ObjectId(orderDto.getId()));
		}
		order.setItemId(orderDto.getItemId());
		order.setItemPrice(orderDto.getItemPrice());
		order.setPurchaseCart(orderDto.getPurchaseCart());
		order.setQuantity(orderDto.getQuantity());
		return order;
	}
	
	//Order to OrderDto
	public OrderDto toOrderDto(Order order) {
		if(order==null) {
			return null;
		}
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId().toString());
		orderDto.setItemId(order.getItemId());
		orderDto.setItemPrice(order.getItemPrice());
		orderDto.setOrderTotalPrice(order.getOrderTotalPrice());
		orderDto.setPurchaseCart(order.getPurchaseCart());
		orderDto.setQuantity(order.getQuantity());
		return orderDto;
	}
	//Orders to OrdersDto
	public List<OrderDto> toOrdersDto(List<Order> orders){
		if(orders==null) {
			return null;
		}
		List<OrderDto> ordersDto = orders.stream().map(this::toOrderDto).collect(Collectors.toList());
		return ordersDto;
	}
	
	public Order mapOrderDtoAndItemDtoToOrder(ItemDto itemDto,OrderDto orderDto,BigDecimal totalOrderPrice) {
		if(itemDto==null || orderDto==null) {
			return null;
		}
		Order order = new Order();
		order.setItemId(itemDto.getId());
		order.setItemPrice(itemDto.getPrice());
		order.setOrderTotalPrice(totalOrderPrice);
		order.setPurchaseCart(orderDto.getPurchaseCart());
		order.setQuantity(orderDto.getQuantity());
		return order;
		
		
	}
	

}
