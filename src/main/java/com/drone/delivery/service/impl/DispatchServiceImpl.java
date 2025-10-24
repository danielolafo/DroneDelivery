package com.drone.delivery.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.drone.delivery.dto.CartHistory;
import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.dto.ResponseWrapper;
import com.drone.delivery.entity.Dispatches;
import com.drone.delivery.mapper.DispatchMapper;
import com.drone.delivery.repository.DispatchRepository;
import com.drone.delivery.service.DispatchCartService;
import com.drone.delivery.service.DispatchService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DispatchServiceImpl implements DispatchService {

	private DispatchRepository repository;

	private DispatchCartService dispatchCartService;

	public DispatchServiceImpl(DispatchRepository repository, DispatchCartService dispatchCartService) {
		this.repository = repository;
		this.dispatchCartService = dispatchCartService;
	}

	@Override
	public Mono<ResponseWrapper<DispatchDto>> dispatch(DispatchDto dispatchDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<DispatchDto> getDispatchHistory(Integer customerId) {
		log.info("getDispatchHistory - : customerId "+customerId);

		Flux<DispatchCartDto> dispatchCartDtos = this.dispatchCartService.getDispatchContent(customerId);
		
		this.dispatchCartService.getDispatchContent(customerId)
		.map(dis -> DispatchDto.builder()
				.id(dis.getId())
				.build());
		
		return this.repository.findAll().flatMap(dis ->
			dispatchCartDtos
			.filter(dc -> Objects.nonNull(dc.getDispatchId()) 
					&& dc.getDispatchId().equals(dis.getId()))
			.collectList()
			.map(cart -> {
				DispatchDto dto = DispatchMapper.INSTANCE.toDto(dis);
				dto.setLstDispatchCartDto(cart);
				return dto;
			})
		);
		
		/*
		return this.repository.findAll()
		.map(dis -> DispatchDto.builder()
				.droneId(null)
				.id(dis.getId())
				.startDate(dis.getStartDate())
				.endDate(dis.getEndDate())
				.build()
				);
		*/
	
		//return dispatchDtos;
	}

	@Override
	public Flux<Dispatches> getDispatchHistoryPlain(Integer customerId) {
		// TODO Auto-generated method stub
		return this.repository.findAll();
		//return null;
	}

	@Override
	public Flux<CartHistory> getHistory(Integer customerId) {
		log.info("getDispatchHistory - : customerId "+customerId);
		Flux<DispatchDto> dispatchDtos = this.repository.findAll()
				.map(dis -> DispatchDto.builder()
						.droneId(null)
						.id(dis.getId())
						.startDate(dis.getStartDate())
						.endDate(dis.getEndDate())
						.build()
						);

		Flux<DispatchCartDto> dispatchCartDtos = this.dispatchCartService.getDispatchContent(customerId);
		
		dispatchCartDtos.subscribe(
				c -> System.out.println("ABCD"),
				error -> System.out.println("ABCD ERROR "+error.getLocalizedMessage())
		);
		
		return dispatchDtos.flatMap(dispatchDto ->
			dispatchCartDtos.filter(dispatchCartDto -> 
			dispatchCartDto.getDispatchId().equals(dispatchDto.getId()))
			.map(dispatchCartDto -> CartHistory.builder()
					.dispatchCartDto(dispatchCartDto)
					.dispatchDto(dispatchDto)
					.build())
		).onErrorResume(IllegalArgumentException.class, e -> {
           System.err.println("Handling error: " + e.getMessage());
           return Flux.just(CartHistory.builder().build());
        });

	
	}

	@Override
	public Mono<ResponseWrapper<DispatchDto>> create(DispatchDto dispatchDto) {
		log.info("{} : {}", Thread.currentThread().getStackTrace()[1].getMethodName(), dispatchDto);
		
		Mono<Dispatches> dispatchesMono = this.repository.findByOriginAndTargetAndStartDate(dispatchDto.getOrigin(), dispatchDto.getTarget(), dispatchDto.getStartDate());
		dispatchesMono.hasElement().map(u -> Mono.error(new Exception("Only can send one delivery at day")));
		Dispatches dispatches = DispatchMapper.INSTANCE.toEntity(dispatchDto);
		dispatchesMono.subscribe(m ->System.out.println("Found "+m.getId()));
		
		//TEMPORAL
		this.repository.findByOriginAndTargetAndStartDate(dispatchDto.getOrigin(), dispatchDto.getTarget(), dispatchDto.getStartDate())
		.flatMap(d -> {
			return Mono.error(new Exception("Only can send one delivery at day"));//Mono.just(d);
		}).switchIfEmpty(
				this.repository.save(dispatches).map(d->{
					dispatchDto.setId(d.getId());
					return Mono.just(ResponseWrapper.<DispatchDto>builder()
							.data(Objects.nonNull(dispatches.getId()) ? dispatchDto : DispatchDto.builder().build())
							.build());
				})
		).map(d -> Mono.just(ResponseWrapper.<DispatchDto>builder()
				.data(Objects.nonNull(dispatches.getId()) ? dispatchDto : DispatchDto.builder().build())
				.build()));
		
		/*
		Mono<Object> resp =  this.repository.findByOriginAndTargetAndStartDate(dispatchDto.getOrigin(), dispatchDto.getTarget(), dispatchDto.getStartDate())
		.map(d->{
			if(Objects.nonNull(d.getId())) {
				return Mono.just(ResponseWrapper.<DispatchDto>builder()
						.data(Objects.nonNull(dispatches.getId()) ? dispatchDto : DispatchDto.builder().build())
						.build());
			}else {
				return Mono.just(ResponseWrapper.<DispatchDto>builder()
						.data(Objects.nonNull(dispatches.getId()) ? dispatchDto : DispatchDto.builder().build())
						.build());
			}
		});
		*/
		
		//resp.subscribe(s -> System.out.println("Mono info "+s.getClass()));
			
		return Mono.just(ResponseWrapper.<DispatchDto>builder()
				.data(Objects.nonNull(dispatches.getId()) ? dispatchDto : DispatchDto.builder().build())
				.build());
		
	}
	

}
