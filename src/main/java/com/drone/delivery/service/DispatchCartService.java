package com.drone.delivery.service;

import com.drone.delivery.dto.DispatchCartDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DispatchCartService {
	
	public Flux<DispatchCartDto> getDispatchContent(Integer dispatchId);
	
	public Mono<DispatchCartDto> create(DispatchCartDto dispatchCartDto);

}
