package com.drone.delivery.service.impl;

import org.springframework.stereotype.Service;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.repository.DispatchCartRepository;
import com.drone.delivery.service.DispatchCartService;

import reactor.core.publisher.Flux;

@Service
public class DispatchCartServiceImpl implements DispatchCartService {
	
	private final DispatchCartRepository repository;
	
	public DispatchCartServiceImpl(DispatchCartRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<DispatchCartDto> getDispatchContent(Integer dispatchId) {
		return this.repository.findByDispatch_Id(dispatchId)
		.map(dis -> {
			DispatchCartDto dispatchCartDto = new  DispatchCartDto();
			dispatchCartDto.setId(dispatchId);
			return dispatchCartDto;
		});
	}

}
