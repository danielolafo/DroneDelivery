package com.drone.delivery.service.impl;

import java.util.stream.Collectors;

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
//		return this.repository.findAll()
//				.map(dis -> DispatchDto.builder()
//						.droneId(null)
//						.id(dis.getId())
//						.startDate(dis.getStartDate())
//						.endDate(dis.getEndDate())
//						.build()
//						);
		Flux<DispatchCartDto> dispatchCartDtos = this.dispatchCartService.getDispatchContent(customerId);
		return dispatchDtos.map(dis -> {
			dis.setLstDispatchCartDto(dispatchCartDtos.collectList().block().stream()
					.filter(dc -> dc.getDispatchId().equals(dis.getId())).collect(Collectors.toList()));
			return dis;
		});
		//return null;
		
		
		/*
		 * 
		log.info("getDispatchHistory - : customerId "+customerId);
		Flux<DispatchDto> dispatchDtos = this.repository.findByCustomer_Id(customerId)
				.map(dis -> DispatchDto.builder().build());
		Flux<DispatchCartDto> dispatchCartDtos = this.dispatchCartService.getDispatchContent(customerId);
		return dispatchDtos.map(dis -> {
			dis.setLstDispatchCartDto(dispatchCartDtos.collectList().block().stream()
					.filter(dc -> dc.getDispatchId().equals(dis.getId())).collect(Collectors.toList()));
			return dis;
		});
		return null;
		 */
	}

	@Override
	public Flux<Dispatches> getDispatchHistoryPlain(Integer customerId) {
		// TODO Auto-generated method stub
		return this.repository.findAll();
		//return null;
	}
	

}
