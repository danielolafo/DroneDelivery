package com.drone.delivery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.service.DispatchCartService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dispatch-cart")
public class DispatchCartController {
	
	private DispatchCartService dispatchCartService;
	
	public DispatchCartController(DispatchCartService dispatchCartService) {
		this.dispatchCartService = dispatchCartService;
	}
	
	public Mono<DispatchCartDto> createCart(DispatchCartService dispatchCartService){
		return this.dispatchCartService.create(null);
	}

}
