package com.sbasly.microservice.inventory.mapper;

import com.sbasly.microservice.inventory.dto.InventoryRequest;
import com.sbasly.microservice.inventory.dto.InventoryResponse;
import com.sbasly.microservice.inventory.model.Inventory;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;


@Mapper(
		componentModel = MappingConstants.ComponentModel.SPRING,
		injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface InventoryMapper {
	InventoryResponse toResponse(Inventory entity);

	Inventory toEntity(InventoryRequest dto);

	void updateFromDto(InventoryRequest dto, @MappingTarget Inventory entity);
}