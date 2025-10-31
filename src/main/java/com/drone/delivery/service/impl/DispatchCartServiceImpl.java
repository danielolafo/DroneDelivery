package com.drone.delivery.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.entity.DispatchCart;
import com.drone.delivery.mapper.DispatchCartMapper;
import com.drone.delivery.repository.DispatchCartRepository;
import com.drone.delivery.service.DispatchCartService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
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
	public Mono<DispatchCartDto> create(@Validated DispatchCartDto dispatchCartDto) {
		log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), dispatchCartDto);
		return this.findByDispatchAndProduct(dispatchCartDto.getDispatchId(), dispatchCartDto.getProductId())
		.map(dis ->{
			log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), "This product has been already added to the cart");
			return DispatchCartDto.builder().build();
		}).switchIfEmpty(this.save(DispatchCartMapper.INSTANCE.toEntity(dispatchCartDto)));
	}
	
	public Mono<DispatchCart> findByDispatchAndProduct(Integer dispatchId, Integer productId){
		return this.repository.findByDispatchIdAndProductId(dispatchId, productId);
	}
	
	public Mono<DispatchCartDto> save(DispatchCart dispatchCart) {
		log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), dispatchCart);
//		this.repository
//				.save(dispatchCart).subscribe(s->System.out.println("CALLING SUBSCRIBE"));
		return this.repository
				.save(dispatchCart)
				.map(dis -> {
					log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), "DispatchCart saved");
					DispatchCartDto dispatchCartDto = new DispatchCartDto();
					dispatchCartDto.setId(dis.getId());
					return dispatchCartDto;
				});
	}

}
