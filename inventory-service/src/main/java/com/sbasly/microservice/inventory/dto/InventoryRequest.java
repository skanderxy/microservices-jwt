package com.sbasly.microservice.inventory.dto;

public record InventoryRequest(String skuCode, Integer quantity) {
}