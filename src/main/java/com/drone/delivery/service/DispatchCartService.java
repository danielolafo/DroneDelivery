package com.drone.delivery.service;

import com.drone.delivery.dto.DispatchCartDto;

import reactor.core.publisher.Flux;

public interface DispatchCartService {
	
	public Flux<DispatchCartDto> getDispatchContent(Integer dispatchId);

}
