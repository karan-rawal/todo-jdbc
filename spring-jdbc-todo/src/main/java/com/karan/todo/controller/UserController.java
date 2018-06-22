package com.karan.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.ResponseObject;
import com.karan.todo.model.User;
import com.karan.todo.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/register")
	public ResponseEntity<ResponseObject> registerUser(@RequestBody User user) throws UserException {
		int userID;
		userID = userService.createUser(user);
		
		User userWithId = new User();
		userWithId.setId(userID);
		
		ResponseObject response = new ResponseObject(userWithId);
		return new ResponseEntity<ResponseObject>(response, HttpStatus.OK);
	}
}
