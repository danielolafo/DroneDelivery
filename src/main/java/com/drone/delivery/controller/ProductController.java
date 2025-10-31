package com.drone.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.dto.ResponseWrapper;
import com.drone.delivery.service.ProductService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("")
	public Flux<ProductDto> findAll(){
		return this.productService.getProducts();
	}
	
	@PostMapping()
	public Mono<ResponseWrapper<ProductDto>> create(@Valid @RequestBody ProductDto productDto){
		return this.productService.create(productDto);
	}

}
