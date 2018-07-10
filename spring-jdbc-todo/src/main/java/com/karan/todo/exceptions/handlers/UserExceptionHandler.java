package com.karan.todo.exceptions.handlers;


import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleException(UserException e) {
        ResponseObject response = new ResponseObject(e.getErrorCode(), e.getMessage(), e.getData());
        System.out.println("Error");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (e.getErrorCode() == UserException.CODE_USER_VALIDATION) {
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(response, status);
    }

}
