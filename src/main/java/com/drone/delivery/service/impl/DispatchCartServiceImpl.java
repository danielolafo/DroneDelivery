package com.drone.delivery.service.impl;

import org.springframework.stereotype.Service;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.mapper.DispatchCartMapper;
import com.drone.delivery.repository.DispatchCartRepository;
import com.drone.delivery.service.DispatchCartService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DispatchCartServiceImpl implements DispatchCartService {
	
	private final DispatchCartRepository repository;
	
	public DispatchCartServiceImpl(DispatchCartRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<DispatchCartDto> getDispatchContent(Integer dispatchId) {
		return this.repository.findByDispatchId(dispatchId)
		.map(dis -> {
			System.out.println("dispatch_cart_serviceimpl "+dis.getDispatchId());
			DispatchCartDto dispatchCartDto = DispatchCartMapper.INSTANCE.toDto(dis);
			return dispatchCartDto;
		}).onErrorContinue((el, err )-> System.out.println("ERROR "+el.getLocalizedMessage()));
	}

	@Override
	public Mono<DispatchCartDto> create(DispatchCartDto dispatchCartDto) {
		return this.repository
				.save(DispatchCartMapper.INSTANCE.toEntity(dispatchCartDto))
				.map(dis -> {
					dispatchCartDto.setId(dis.getId());
					return dispatchCartDto;
				});
	}

}
