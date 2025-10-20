package com.drone.delivery.service.impl;

import org.springframework.stereotype.Service;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.repository.ProductRepository;
import com.drone.delivery.service.ProductService;

import reactor.core.publisher.Flux;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository repository;
	
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<ProductDto> getProducts() {
		return this.repository.findAll()
		.map(p -> ProductDto.builder().brand(p.getBrand()).id(p.getId()).name(p.getName()).build());
		//return null;
	}

}
