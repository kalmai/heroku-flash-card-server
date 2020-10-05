package com.example.users;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class JdbcUserDao implements UserDao{
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
    public JdbcUserDao(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }
	
	public User verifyUser(String userName) {
		User user = new User();
		
		String sql = "SELECT user_id, user_name FROM users where user_name = ?";
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql,userName);
		row.next();
		user.setUser_id(row.getInt("user_id"));
		user.setUser_name(row.getString("user_name"));
		
		return user;
	}
	
	public void createUser(String newUser) {
		String sql = "insert into users values (default, ?)";
		jdbcTemplate.update(sql,newUser);
	}
	
	public void deleteUser(int userId) {
		String sql = "delete from users where (user_id) = ?";
		jdbcTemplate.update(sql,userId);
	}
		
}
