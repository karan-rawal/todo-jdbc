package com.karan.todo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.karan.todo.exceptions.UserException;
import com.karan.todo.model.User;
import com.karan.todo.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	private static final String CONFIRM_PASSWORD = "confirmPassword";

	private static final String PASSWORD = "password";

	private static final String EMAIL = "dummyemail@email.com";

	private static final String LNAME = "lname";

	private static final String FNAME = "fname";
	
	private User user;

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserRepository userRepository;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Before
	public void beforeEach() {
		user = new User();
		user.setFname(FNAME);
		user.setLname(LNAME);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		user.setConfirmPassword(PASSWORD);
	}
	
	@Test
	public void testCreateUserSuccess() throws UserException {
		Mockito.when(userRepository.createUser(user)).thenReturn(1);
		Assert.assertEquals(1, userService.createUser(user));
	}
	
	@Test
	public void testCreateUserPasswordMissmatch() throws UserException {
		user.setConfirmPassword(CONFIRM_PASSWORD);

		Mockito.when(userRepository.createUser(user)).thenReturn(1);
		expectedException.expect(UserException.class);
		expectedException.expectMessage(UserException.PASSWORD_MISSMATCH);
		
		userService.createUser(user);
	}
	
	@Test
	public void testCreateUserAlreadyExists() throws UserException {
		Mockito.when(userRepository.createUser(user)).thenReturn(1);
		Mockito.when(userRepository.getUserByEmail(user.getEmail())).thenReturn(user);
		
		expectedException.expect(UserException.class);
		expectedException.expectMessage(UserException.USER_EXISTS);
		
		userService.createUser(user);
	}

	@Test
	public void testGetUserByEmail() throws UserException {
		Mockito.when(userRepository.getUserByEmail(user.getEmail())).thenReturn(user);
		User result = userService.getUserByEmail(user.getEmail());
		
		Assert.assertEquals(result, user);
	}
	
	@Test
	public void testGetUserByEmailNotFound() throws UserException {
		Mockito.when(userRepository.getUserByEmail(user.getEmail())).thenReturn(null);
		expectedException.expect(UserException.class);
		expectedException.expectMessage(UserException.USER_NOT_FOUND);
		
		userService.getUserByEmail(user.getEmail());
	}

}
