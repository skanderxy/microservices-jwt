package com.sbasly.microservice.inventory.service;

import com.sbasly.microservice.inventory.dto.InventoryRequest;
import com.sbasly.microservice.inventory.dto.InventoryResponse;
import com.sbasly.microservice.inventory.mapper.InventoryMapper;
import com.sbasly.microservice.inventory.model.Inventory;
import com.sbasly.microservice.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class InventoryService {

	private final InventoryRepository repository;
	private final InventoryMapper mapper;
	private final InventoryRepository inventoryRepository;


	public List<InventoryResponse> findAll() {
		return repository.findAll()
				.stream()
				.map(mapper::toResponse)
				.toList();
	}

	public Optional<InventoryResponse> findById(Long id) {
		return repository.findById(id).map(mapper::toResponse);
	}

	public InventoryResponse save(InventoryRequest request) {
		Inventory entity = mapper.toEntity(request);
		return mapper.toResponse(repository.save(entity));
	}

	public InventoryResponse update(Long id, InventoryRequest request) {
		Inventory existingEntity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Inventory not found with id " + id));

		mapper.updateFromDto(request, existingEntity);

		Inventory savedEntity = repository.save(existingEntity);
		return mapper.toResponse(savedEntity);
	}


	public void delete(Long id) {
		repository.deleteById(id);
	}

	public boolean isInStock(String skuCode, Integer quantity) {
		return repository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
	}
}