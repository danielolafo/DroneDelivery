package com.drone.delivery.service;



import com.drone.delivery.dto.CartHistory;
import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.dto.ResponseWrapper;
import com.drone.delivery.entity.Dispatches;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DispatchService {
	
	public Mono<ResponseWrapper<DispatchDto>> dispatch(DispatchDto dispatchDto);
	
	public Flux<DispatchDto> getDispatchHistory(Integer customerId);
	
	public Flux<Dispatches> getDispatchHistoryPlain(Integer customerId);
	
	public Flux<CartHistory> getHistory(Integer customerId);
	
	public Mono<ResponseWrapper<DispatchDto>> create(DispatchDto dispatchDto);

}
