package com.drone.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.service.ProductService;

import reactor.core.publisher.Flux;

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

}
