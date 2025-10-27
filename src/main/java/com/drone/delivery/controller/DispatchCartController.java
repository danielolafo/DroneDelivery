package com.drone.delivery.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.service.DispatchCartService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dispatch-cart")
@Validated
public class DispatchCartController {
	
	private DispatchCartService dispatchCartService;
	
	public DispatchCartController(DispatchCartService dispatchCartService) {
		this.dispatchCartService = dispatchCartService;
	}
	
	@PostMapping
	public Mono<DispatchCartDto> createCart(@Valid @RequestBody DispatchCartDto dispatchCartDto){
		return this.dispatchCartService.create(dispatchCartDto);
	}

}
