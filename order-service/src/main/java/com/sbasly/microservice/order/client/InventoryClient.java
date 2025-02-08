package com.sbasly.microservice.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8116")
public interface InventoryClient {

	@GetMapping("/api/inventory/is-in-stock")
	boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
