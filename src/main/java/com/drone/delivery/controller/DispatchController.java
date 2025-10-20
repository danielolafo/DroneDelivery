package com.drone.delivery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.entity.Dispatches;
import com.drone.delivery.service.DispatchService;

import reactor.core.publisher.Flux;

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
	
	@GetMapping("/history-plain/{customerId}")
	public Flux<Dispatches> getDispatchHistoryPlain(@PathVariable("customerId") Integer customerId){
		return this.dispatchService.getDispatchHistoryPlain(customerId);
	}

}
