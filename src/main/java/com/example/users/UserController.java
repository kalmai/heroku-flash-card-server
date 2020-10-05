package com.example.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserDao dao;
	
	public UserController(UserDao dao) {
		this.dao = dao;
	}
	
	@ResponseStatus(value = HttpStatus.OK)	
	@RequestMapping(path="/login/{userName}",method = RequestMethod.GET)
	public User verifyUser(@PathVariable String userName) {
		return dao.verifyUser(userName);
	}
	
	@RequestMapping(path="/create-user/{newUser}", method= RequestMethod.POST)
	public void createUser(@PathVariable String newUser) {
		dao.createUser(newUser);
	}
	
	@RequestMapping(path="/delete-user/{userId}",method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable int userId) {
		dao.deleteUser(userId);
	}
	
}
