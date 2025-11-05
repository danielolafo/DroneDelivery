package com.drone.delivery.service.impl;

import java.util.ArrayList;
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
	public Flux<DispatchDto> getDispatchHistory(UUID customerId) {
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
	public Flux<Dispatches> getDispatchHistoryPlain(UUID customerId) {
		// TODO Auto-generated method stub
		return this.repository.findAll();
		//return null;
	}

	@Override
	public Flux<CartHistory> getHistory(UUID customerId) {
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
		dispatchDto.setId(UUID.randomUUID());
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
			
		}).switchIfEmpty(this.save(dispatchDto));
		
		
	}
	
	
	/**
	 * <p>Saves a Dispatch entity into the database</p>
	 * @param dispatches
	 * @return The <strong>new saved</strong> entity
	 * @author Daniel Orlando López Ochoa
	 */
	public Mono<ResponseWrapper<DispatchDto>> save(Dispatches dispatches){
		
		return this.repository.save(dispatches).map(d->{
			DispatchDto dispatchDto = DispatchMapper.INSTANCE.toDto(d);
			
			return ResponseWrapper.<DispatchDto>builder()
					.data(dispatchDto)
					.message("OK")
					.build();
		});
		
	}
	
	public Mono<ResponseWrapper<DispatchDto>> save(DispatchDto dispatchDto){
		return this.save(DispatchMapper.INSTANCE.toEntity(dispatchDto)).map(dis->{
			dispatchDto.getLstDispatchCartDto().forEach(cart -> {
				log.info("Asigning ID "+dis.getData().getId());
				//this.dispatchCartService.create(cart).subscribe(s->System.out.println("Called dispatchCart save"));
				
				this.dispatchCartService.create(cart).map(c->{
					log.debug("Saved cart ***");
					return null;
				}).doOnError(s->{
					log.info("DO ON ERORR");
				}).subscribe();
				
			});
			return ResponseWrapper.<DispatchDto>builder()
					.data(dispatchDto)
					.message("OK")
					.build();
		});
	}
	
	
	public Mono<Dispatches> empty(){
		log.info("Mono Empty");
		return Mono.empty();
	}

	@Override
	public Flux<DispatchDto> getAllHistory() {
		Flux<DispatchDto> dispatches = this.repository.findAll().map(d-> DispatchMapper.INSTANCE.toDto(d));
		Flux<DispatchCartDto> dispatchCart = this.dispatchCartService.getAllHistory();
		return dispatches.flatMap(dis->{
			dis.setLstDispatchCartDto(new ArrayList<>());
			log.info("Dis.id {}", dis.getId());
			return dispatchCart.filter(dc-> dc.getDispatchId().equals(dis.getId()))
			.collectList()
			.map(dc-> {
				//log.info("DispatchId : {}, Cart dipsatch id: {} - ARE EQUAL? {}",dis.getId(), dc.getDispatchId(), dis.getId().equals(dc.getDispatchId()));
				log.info("Setting dispatchCart to List****");
				dis.setLstDispatchCartDto(dc);
				log.info("dis.getList().size() 1 {} ", dis.getLstDispatchCartDto().size());
				return dis;
			});
			//log.info("dis.getList().size() 2 {} ", dis.getLstDispatchCartDto().size());
			//return dis;
		});
		
	}


}
