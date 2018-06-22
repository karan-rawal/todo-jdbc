package com.karan.todo.utils.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.karan.todo.model.User;

public class UserRowMapper implements RowMapper<User>{

	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_EMAIL = "email";
	private static final String COLUMN_LNAME = "lname";
	private static final String COLUMN_FNAME = "fname";
	private static final String COLUMN_ID = "id";

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(COLUMN_ID));
		user.setFname(rs.getString(COLUMN_FNAME));
		user.setLname(rs.getString(COLUMN_LNAME));
		user.setEmail(rs.getString(COLUMN_EMAIL));
		user.setPassword(rs.getString(COLUMN_PASSWORD));
		return user;
	}

}
