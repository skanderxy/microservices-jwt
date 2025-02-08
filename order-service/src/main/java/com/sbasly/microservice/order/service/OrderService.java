package com.sbasly.microservice.order.service;

import com.sbasly.microservice.order.client.InventoryClient;
import com.sbasly.microservice.order.dto.OrderRequest;
import com.sbasly.microservice.order.model.Order;
import com.sbasly.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final InventoryClient inventoryClient;

	public OrderRequest placeOrder(OrderRequest orderRequest) {
		if (inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity())) {


			Order order = Order.builder().orderNumber(UUID.randomUUID().toString())
					.skuCode(orderRequest.skuCode())
					.price(orderRequest.price())
					.quantity(orderRequest.quantity())
					.build();
			Order saveOrder = orderRepository.save(order);
			return new OrderRequest(saveOrder.getId(), saveOrder.getOrderNumber(), orderRequest.skuCode(), orderRequest.price(), orderRequest.quantity());
		}
		throw new RuntimeException("Not enough stock");
	}
}
