package com.karan.todo.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.ResponseObject;

@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ResponseObject> handleException(UserException e) {
		ResponseObject response = new ResponseObject(e.getErrorCode(), e.getMessage(), e.getData());
		return new ResponseEntity<ResponseObject>(response, HttpStatus.BAD_REQUEST);
	}
}
