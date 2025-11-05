package com.drone.delivery.service;



import java.util.UUID;

import com.drone.delivery.dto.CartHistory;
import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.dto.ResponseWrapper;
import com.drone.delivery.entity.Dispatches;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DispatchService {
	
	public Mono<ResponseWrapper<DispatchDto>> dispatch(DispatchDto dispatchDto);
	
	public Flux<DispatchDto> getDispatchHistory(UUID customerId);
	
	public Flux<Dispatches> getDispatchHistoryPlain(UUID customerId);
	
	public Flux<CartHistory> getHistory(UUID customerId);
	
	public Mono<ResponseWrapper<DispatchDto>> create(DispatchDto dispatchDto);
	
	public Flux<DispatchDto> getAllHistory();
	

}
