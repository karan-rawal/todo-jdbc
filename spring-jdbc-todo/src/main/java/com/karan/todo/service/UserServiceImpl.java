package com.karan.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.User;
import com.karan.todo.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public int createUser(User user) throws UserException {
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new UserException(UserException.PASSWORD_MISSMATCH, UserException.CODE_PASSWORD_MISSMATCH);
		}
		if (userRepository.getUserByEmail(user.getEmail()) != null) {
			throw new UserException(UserException.USER_EXISTS, UserException.CODE_USER_EXISTS, user);
		}
		return userRepository.createUser(user);
	}

	@Override
	public User getUserByEmail(String email) throws UserException {
		User user = userRepository.getUserByEmail(email);
		if(user == null) {
			throw new UserException(UserException.USER_NOT_FOUND, UserException.CODE_USER_NOT_FOUND);
		}
		return user;
	}
}
