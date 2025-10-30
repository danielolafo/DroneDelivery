package com.drone.delivery.service.impl;

import java.util.Objects;
import java.util.UUID;

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
		
		return this.repository.findByCustomerId(customerId).flatMap(dis ->
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
		
		return  this.repository.findByOriginAndTargetAndStartDate(dispatchDto.getOrigin(), dispatchDto.getTarget(), dispatchDto.getStartDate())
		.map(d->{
			log.info("d.getId() "+d.getId());
			if(Objects.nonNull(d.getId())) {
				log.error("Only can send onde delivery per day");
				return ResponseWrapper.<DispatchDto>builder()
						.data(DispatchDto.builder().build())
						.message("Only can send onde delivery per day")
						.build();
			}else {
				log.info("OK DISPATCH CREATED");
				this.repository.save(DispatchMapper.INSTANCE.toEntity(dispatchDto)).subscribe(
						saved -> System.out.println("Id "+ saved.getId()),
						error -> System.err.println("Error "+error.getLocalizedMessage())
				);
				return ResponseWrapper.<DispatchDto>builder()
						.data(dispatchDto)
						.message("OK")
						.build();
			}
			
		}).switchIfEmpty(this.save(DispatchMapper.INSTANCE.toEntity(dispatchDto)));
		
		
	}
	
	
	/**
	 * <p>Saves a Dispatch entity into the database</p>
	 * @param dispatches
	 * @return The <strong>new saved</strong> entity
	 * @author Daniel Orlando López Ochoa
	 */
	public Mono<ResponseWrapper<DispatchDto>> save(Dispatches dispatches){
		if(Objects.isNull(dispatches.getUnid())) {
			dispatches.setUnid(UUID.randomUUID());
		}
		
		
		return this.repository.save(dispatches).map(d->{
			DispatchDto dispatchDto = DispatchMapper.INSTANCE.toDto(d);
			this.findByUnid(dispatches.getUnid()).map(s->
			{
			log.info("Calling findByUnid "+dispatches.getId());
			return ResponseWrapper.<DispatchDto>builder()
					.data(dispatchDto)
					.message("OK")
					.build();
			});
			return ResponseWrapper.<DispatchDto>builder()
					.data(dispatchDto)
					.message("OK")
					.build();
		});
		
	}
	
	public Mono<Dispatches> findByUnid(UUID uuid){
		return this.repository.findByUnid(uuid).map(disp -> {
			log.info("disp.id "+disp.getId());
			return disp;
		}).switchIfEmpty(empty());
	}
	
	public Mono<Dispatches> empty(){
		log.info("Mono Empty");
		return Mono.empty();
	}

	@Override
	public Mono<ResponseWrapper<DispatchDto>> getByUnit(UUID unid) {
		return this.findByUnid(unid).map(disp -> ResponseWrapper.<DispatchDto>builder()
				.data(DispatchMapper.INSTANCE.toDto(disp))
				.message("OK")
				.build());
		
	}
	

}
