package com.lord.itemservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandleItemException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(WebClientException.class)
	ResponseEntity<String> handleWebClient(WebClientException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
	}

}
