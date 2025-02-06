package com.sbasly.microservice.product.service;

import com.sbasly.microservice.product.dto.ProductRequest;
import com.sbasly.microservice.product.dto.ProductResponse;
import com.sbasly.microservice.product.model.Product;
import com.sbasly.microservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public ProductResponse save(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.name())
				.description(productRequest.description())
				.price(productRequest.price())
				.build();
		Product savedProduct = productRepository.save(product);

		log.info("Product created: {}", product);
		return new ProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice());
	}

	public Iterable<ProductResponse> findAll() {
		return productRepository.findAll().stream().map(p -> new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice())).toList();
	}
}
