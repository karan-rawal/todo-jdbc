package com.karan.todo.repository;

import com.karan.todo.AppConfig;
import com.karan.todo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfig.class)
@Transactional
@Rollback(true)
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;
    private User user;

    private ExpectedException expectedException;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setFname("Fname");
        user.setLname("Lname");
        user.setEmail("completelynewtest@gmail.com");
        user.setConfirmPassword("Password1#");
        user.setPassword("Password1#");

        expectedException = ExpectedException.none();
    }

    @Test
    public void testCreateUserSuccess() {
        int id = userRepository.createUser(user);

        assertTrue(id > -1);
    }

    @Test
    public void testGetUserById() {
        int id = userRepository.createUser(user);

        User userResult = userRepository.getUserById(id);

        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmail(), userResult.getEmail());
    }

    @Test
    public void testGetUserByEmail() {
        int id = userRepository.createUser(user);
        User userResultById = userRepository.getUserById(id);

        User userResultByEmail = userRepository.getUserByEmail(userResultById.getEmail());

        Assert.assertNotNull(user);
        Assert.assertEquals(userResultByEmail.getEmail(), userResultById.getEmail());
    }
}
