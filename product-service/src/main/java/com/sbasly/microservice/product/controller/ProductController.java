package com.sbasly.microservice.product.controller;

import com.sbasly.microservice.product.dto.ProductRequest;
import com.sbasly.microservice.product.dto.ProductResponse;
import com.sbasly.microservice.product.model.Product;
import com.sbasly.microservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
		return productService.save(productRequest);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<ProductResponse> getProducts() {
		return productService.findAll();
	}
}
