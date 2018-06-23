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

		HttpStatus status = null;

		switch (e.getErrorCode()) {
		case UserException.CODE_USER_VALIDATION:
			status = HttpStatus.NOT_ACCEPTABLE;
			break;
		case UserException.CODE_USER_EXISTS:
		case UserException.CODE_PASSWORD_MISSMATCH:
		case UserException.CODE_USER_NOT_FOUND:
		default:
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<ResponseObject>(response, status);
	}
}
