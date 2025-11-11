package com.drone.delivery.config;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.drone.delivery.dto.ResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CustomExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseWrapper<Set<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		StringBuilder sb = new StringBuilder();
		
		Set<String> lstErrors = new HashSet<>();
		for(ObjectError oe : ex.getAllErrors()) {
			sb.append(oe.getDefaultMessage()).append(" ");
			lstErrors.add(oe.getDefaultMessage());
		}
		log.error("{} {}", Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getLocalizedMessage());
		return new ResponseEntity<>(ResponseWrapper.<Set<String>>builder()
				.message(sb.toString())
				.data(lstErrors)
				.build(), 
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<ResponseWrapper<Boolean>> handleBadRequest(BadRequest ex){
		log.error("{} {}", Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getLocalizedMessage());
		return new ResponseEntity<>(ResponseWrapper.<Boolean>builder()
				.message(ex.getLocalizedMessage())
				.build(), 
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseWrapper<Boolean>> handleException(Exception ex){
		log.error("{} {}", Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getLocalizedMessage());
		log.error("Error +++++ "+ex.getClass().getName());
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String sStackTrace = sw.toString();
		log.error("PrintStackTrace {}", sStackTrace);
		ex.printStackTrace();
		log.error("DANIEL...");
		return new ResponseEntity<>(ResponseWrapper.<Boolean>builder()
				.message(ex.getLocalizedMessage())
				.build(), 
				HttpStatus.CONFLICT);
	}

}
