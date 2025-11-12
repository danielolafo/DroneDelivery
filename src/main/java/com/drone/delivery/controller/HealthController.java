package com.drone.delivery.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/health")
@Slf4j
public class HealthController {
	
	@GetMapping
	public Flux<String> check(){
		log.info("Daniel log for testing on CGR");
		return Flux.just("Emitted on : "+LocalDate.now().toString());
	}
}
