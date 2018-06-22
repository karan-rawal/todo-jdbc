package com.karan.todo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.karan.todo.model.User;
import com.karan.todo.utils.rowmappers.UserRowMapper;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	private static final String SQL_CREATE_USER = "insert into user (fname, lname, email, password) values (?, ?, ?, ?)";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int createUser(User user) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_CREATE_USER, new String[] {"id"});
				ps.setString(1, user.getFname());
				ps.setString(2, user.getLname());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());
				return ps;
			}
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public User getUserByEmail(String email) {
		List<User> users = jdbcTemplate.query("select * from user where email=?", new UserRowMapper(), email);
		if(users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}
	
	@Override
	public User getUserById(int id) {
		List<User> users = jdbcTemplate.query("select * from user where id=?", new UserRowMapper(), id);
		if(users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}
	
}
