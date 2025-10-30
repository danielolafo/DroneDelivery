package com.drone.delivery.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.drone.delivery.dto.ResponseWrapper;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseWrapper<Set<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		StringBuilder sb = new StringBuilder();
		
		Set<String> lstErrors = new HashSet<>();
		for(ObjectError oe : ex.getAllErrors()) {
			sb.append(oe.getDefaultMessage()).append(" ");
			lstErrors.add(oe.getDefaultMessage());
		}
		return new ResponseEntity<>(ResponseWrapper.<Set<String>>builder()
				.message(sb.toString())
				.data(lstErrors)
				.build(), 
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<ResponseWrapper<Boolean>> handleBadRequest(BadRequest ex){
		return new ResponseEntity<>(ResponseWrapper.<Boolean>builder()
				.message(ex.getLocalizedMessage())
				.build(), 
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseWrapper<Boolean>> handleException(Exception ex){
		return new ResponseEntity<>(ResponseWrapper.<Boolean>builder()
				.message(ex.getLocalizedMessage())
				.build(), 
				HttpStatus.CONFLICT);
	}

}
