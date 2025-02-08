package com.sbasly.microservice.inventory.controller;

import com.sbasly.microservice.inventory.dto.InventoryRequest;
import com.sbasly.microservice.inventory.dto.InventoryResponse;
import com.sbasly.microservice.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

	private final InventoryService service;

	@GetMapping
	public List<InventoryResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Optional<InventoryResponse> findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public InventoryResponse create(@RequestBody InventoryRequest request) {
		return service.save(request);
	}

	@PutMapping("/{id}")
	public InventoryResponse update(@PathVariable Long id, @RequestBody InventoryRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/is-in-stock")
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
		return service.isInStock(skuCode, quantity);
	}
}