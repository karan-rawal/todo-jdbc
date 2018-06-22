package com.karan.todo.repository;

import com.karan.todo.model.User;

public interface UserRepository {

	int createUser(User user);

	User getUserByEmail(String email);

	User getUserById(int id);

}