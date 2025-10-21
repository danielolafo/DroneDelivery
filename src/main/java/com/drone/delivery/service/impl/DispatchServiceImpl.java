package com.drone.delivery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.dto.ResponseWrapper;
import com.drone.delivery.entity.Dispatches;
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
		Flux<DispatchDto> dispatchDtos = this.repository.findAll()
				.map(dis -> DispatchDto.builder()
						.droneId(null)
						.id(dis.getId())
						.startDate(dis.getStartDate())
						.endDate(dis.getEndDate())
						.build()
						);

		Flux<DispatchCartDto> dispatchCartDtos = this.dispatchCartService.getDispatchContent(customerId);
//		return dispatchDtos.flatMap(dispatchDto ->
//			dispatchCartDtos.filter(dispatchCartDto -> 
//			dispatchCartDto.getDispatchId().equals(dispatchDto.getId()))
//			.map(dispatchCartDto -> DispatchDto.builder()
//					.droneId(dispatchDto.getId())
//					.lstDispatchCartDto(List.of(dispatchCartDto))
//					.droneId(666)
//					.build())
//		).onErrorResume(IllegalArgumentException.class, e -> {
//            System.err.println("Handling error: " + e.getMessage());
//            return Flux.just(DispatchDto.builder().id(-111).build());
//        });
		
	
		return dispatchDtos.map(dis -> 
			dispatchCartDtos.filter(dispatchCartDto -> 
			dispatchCartDto.getDispatchId().equals(dis.getId()))
			.map(dispatchCartDto -> DispatchDto.builder()
					.droneId(dis.getId())
					//.lstDispatchCartDto(List.of(dispatchCartDto))
					.droneId(666)
					.build()
			)
		);
	}

	@Override
	public Flux<Dispatches> getDispatchHistoryPlain(Integer customerId) {
		// TODO Auto-generated method stub
		return this.repository.findAll();
		//return null;
	}
	

}
