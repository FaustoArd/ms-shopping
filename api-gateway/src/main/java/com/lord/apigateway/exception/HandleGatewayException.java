package com.lord.apigateway.exception;

import java.net.ConnectException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.netflix.discovery.shared.transport.TransportException;

@ControllerAdvice
public class HandleGatewayException extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(HandleGatewayException.class);
	
	@ExceptionHandler(ConnectException.class)
	ResponseEntity<String> handleConnect(ConnectException ex){
		log.error("Micro service connection error");
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TransportException.class)
	ResponseEntity<String> handleTransport(TransportException ex){
		log.error("Discovery server connection error");
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

}
