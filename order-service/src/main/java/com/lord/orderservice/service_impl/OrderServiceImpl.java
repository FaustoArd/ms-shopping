package com.lord.orderservice.service_impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.lord.orderservice.dto.OrderDto;
import com.lord.orderservice.exception.OutOfStockException;
import com.lord.orderservice.mapper.OrderMapper;
import com.lord.orderservice.model.Order;
import com.lord.orderservice.repository.OrderRepository;
import com.lord.orderservice.service.OrderService;

import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private final OrderRepository orderRepository;

	private final WebClient webClient;

	private final OrderMapper orderMapper;

	public OrderServiceImpl(OrderRepository orderRepository, WebClient.Builder webClientBuilder,
			OrderMapper orderMapper) {
		this.orderRepository = orderRepository;
		this.webClient = webClientBuilder.baseUrl("http://localhost:8090").build();
		this.orderMapper = orderMapper;
	}

	@Override
	public BigDecimal placeOrder(OrderDto orderDto)throws OutOfStockException {
		
		if(isInStock(orderDto.getItemId(), orderDto.getQuantity())) {
			Order order = orderMapper.toOrder(orderDto);
			order.setItemPrice(getItemPrice(orderDto.getItemId()));
			order.setOrderTotalPrice(getOrderTotal(order.getItemPrice(), order.getQuantity()));
			return orderRepository.save(order).getOrderTotalPrice();
		}else {
			throw new OutOfStockException("Item out of stock");
		}
		
		

	}

	@Override
	public BigDecimal getItemPrice(String itemId) {

		try {
			Mono<BigDecimal> result = webClient.get()
					.uri("/item/price", uriBuilder -> uriBuilder.queryParam("itemId", itemId).build())
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(BigDecimal.class);
			return result.block();
		} catch (WebClientException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public boolean isInStock(String itemId,int quantity) {
		try {
			Mono<Boolean> result = webClient.get().uri("/stock-item/is-in-stock/{itemId}"
					, uriBuilder -> uriBuilder.queryParam("itemId", itemId).queryParam("quantity", quantity)
					.build()).retrieve().bodyToMono(Boolean.class);
			return result.block();
		}catch(WebClientException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	@Override
	public BigDecimal getOrderTotal(BigDecimal itemPrice, int quantity) {
		return itemPrice.multiply(new BigDecimal(quantity));
	}

	@Override
	public BigDecimal updateOrder(OrderDto orderDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDto> findAllOrders(Long purchaseCartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
