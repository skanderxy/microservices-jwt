package com.sbasly.microservice.inventory.repository;

import com.sbasly.microservice.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantityIsGreaterThan);
}