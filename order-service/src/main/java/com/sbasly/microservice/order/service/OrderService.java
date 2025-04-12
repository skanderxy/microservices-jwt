package com.sbasly.microservice.order.service;

import com.sbasly.microservice.order.dto.OrderRequest;
import com.sbasly.microservice.order.model.Order;
import com.sbasly.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = Order.builder()
				.orderNumber(orderRequest.orderNumber())
				.skuCode(orderRequest.skuCode())
				.price(orderRequest.price())
				.quantity(orderRequest.quantity())
				.build();
		orderRepository.save(order);
	}
}
