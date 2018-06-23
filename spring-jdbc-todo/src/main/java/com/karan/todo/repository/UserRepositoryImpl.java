package com.karan.todo.repository;

import com.karan.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_CREATE_USER = "insert into user (fname, lname, email, password) values (?, ?, ?, ?)";
    @Autowired
    private HibernateTemplate template;

    @Override
    public int createUser(User user) {
        Integer id = (Integer) template.save(user);
        return id;
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> users = (List<User>) (List<?>) template.find("from User where email = ?0", email);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserById(int id) {
        return template.get(User.class, id);
    }

}
