package com.drone.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.delivery.dto.CartHistory;
import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.dto.ResponseWrapper;
import com.drone.delivery.service.DispatchService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {
	
	private DispatchService dispatchService;
	
	public DispatchController(DispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}
	
	@GetMapping("/history/{customerId}")
	public Flux<DispatchDto> getDispatchHistory(@PathVariable("customerId") Integer customerId){
		return this.dispatchService.getDispatchHistory(customerId);
	}
	
	@GetMapping("/cart/{customerId}")
	public Flux<CartHistory> getDispatchHistoryPlain(@PathVariable("customerId") Integer customerId){
		return this.dispatchService.getHistory(customerId);
	}
	
	@PostMapping
	public Mono<ResponseWrapper<DispatchDto>> create(@RequestBody DispatchDto dispatchDto){
		return this.dispatchService.create(dispatchDto);
	}

}
