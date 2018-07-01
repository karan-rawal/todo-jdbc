package com.karan.todo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karan.todo.exceptions.UserException;
import com.karan.todo.exceptions.handlers.GlobalExceptionHandler;
import com.karan.todo.model.User;
import com.karan.todo.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private static final String USER_REGISTER_API = "/user/register";

	@Mock
	private UserServiceImpl userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	private User user;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.setControllerAdvice(new GlobalExceptionHandler()).build();
		user = new User();
		user.setFname("Fname");
		user.setLname("Lname");
		user.setEmail("test@gmail.com");
		user.setConfirmPassword("Password1#");
		user.setPassword("Password1#");
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testRegisterInvalidEmail() throws Exception {
		Mockito.when(userService.createUser(user)).thenReturn(1);
		user.setEmail("");
		mockMvc.perform(post(USER_REGISTER_API).contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()))
				.andExpect(jsonPath("$.errorCode").value(UserException.CODE_USER_VALIDATION))
				.andExpect(jsonPath("$.data[0]", containsString("email"))); // eheck if error is related to email
	}

	@Test
	public void testRegisterValidUser() throws Exception {
		Mockito.when(userService.createUser(user)).thenReturn(1);
		mockMvc.perform(post(USER_REGISTER_API).contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk());
	}

}
