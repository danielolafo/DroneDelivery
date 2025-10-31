package com.drone.delivery.service;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.dto.ResponseWrapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	
	public Flux<ProductDto> getProducts();
	
	public Mono<ResponseWrapper<ProductDto>> create(ProductDto productDto);

}
