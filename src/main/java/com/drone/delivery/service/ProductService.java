package com.drone.delivery.service;

import com.drone.delivery.dto.ProductDto;

import reactor.core.publisher.Flux;

public interface ProductService {
	
	public Flux<ProductDto> getProducts();

}
