package com.sbasly.microservice.product.service;

import com.sbasly.microservice.product.dto.ProductRequest;
import com.sbasly.microservice.product.dto.ProductResponse;
import com.sbasly.microservice.product.model.Product;
import com.sbasly.microservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductResponse createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();

		productRepository.save(product);
		log.info("Product created successfully: {}", product);

		return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
	}

	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll()
				.stream()
				.map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
				.toList();
	}
}
