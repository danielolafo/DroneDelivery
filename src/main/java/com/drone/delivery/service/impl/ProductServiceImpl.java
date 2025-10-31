package com.drone.delivery.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.dto.ResponseWrapper;
import com.drone.delivery.entity.Products;
import com.drone.delivery.mapper.ProductMapper;
import com.drone.delivery.repository.ProductRepository;
import com.drone.delivery.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
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

	@Override
	public Mono<ResponseWrapper<ProductDto>> create(ProductDto productDto) {
		return this.repository.findByNameAndBrand(productDto.getName(), productDto.getBrand())
		.collectList().map(r->ResponseWrapper.<ProductDto>builder()
				.data(productDto)
				.statusCode(HttpStatus.CONFLICT.value())
				.build()).switchIfEmpty(save(productDto));
		/*
		.flatMap(prod -> ResponseWrapper.<ProductDto>builder()
				.data(productDto)
				.statusCode(HttpStatus.CONFLICT.value())
				.build());
				*/
	}
	
	public Mono<ResponseWrapper<ProductDto>> save(ProductDto productDto){
		log.info("{} {}", Thread.currentThread().getStackTrace()[1].getMethodName(), "Saving product");
		UUID uuid = UUID.randomUUID();
		productDto.setId(uuid);
		this.repository.save(ProductMapper.INSTANCE.toEntity(productDto)).subscribe(
		s-> System.out.println("saved product "+ s)		
		);
		return this.repository.save(ProductMapper.INSTANCE.toEntity(productDto))
				.map(p -> {
					log.info("{} {}", Thread.currentThread().getStackTrace()[1].getMethodName(), "Saved product");
					return ResponseWrapper.<ProductDto>builder()
							.data(productDto)
							.statusCode(HttpStatus.CONFLICT.value())
							.build();
				});
	}

}
