package com.karan.todo.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.karan.todo.model.ResponseObject;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ResponseObject> handleException(RuntimeException e) {
		ResponseObject errorResponse = new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		return new ResponseEntity<ResponseObject>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
