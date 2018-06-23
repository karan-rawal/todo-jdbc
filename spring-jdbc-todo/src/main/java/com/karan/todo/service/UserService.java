package com.karan.todo.service;

import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.User;

public interface UserService {

	int createUser(User user) throws UserException;

	User getUserByEmail(String email) throws UserException;

}