package com.drone.delivery.controller;

import java.time.Duration;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/health")
public class HealthController {
	
	@GetMapping
	public Flux<String> check(){
		return Flux.just("Emitted on : "+LocalDate.now().toString());
	}
}
