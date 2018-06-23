package com.karan.todo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.User;
import com.karan.todo.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/register")
	public ResponseEntity<Object> registerUser(@RequestBody @Valid User user, BindingResult result)
			throws UserException {

		if (result.hasErrors()) {
			List<String> errors = getFormattedErrorsList(result);
			throw new UserException(UserException.USER_VALIDATION, UserException.CODE_USER_VALIDATION, errors);
		}

		userService.createUser(user);
		return ResponseEntity.ok().build();
	}

	private List<String> getFormattedErrorsList(BindingResult result) {
		List<String> errors = result.getFieldErrors().stream().map(f -> f.getField() + ": " + f.getDefaultMessage())
				.collect(Collectors.toList());
		return errors;
	}
}
