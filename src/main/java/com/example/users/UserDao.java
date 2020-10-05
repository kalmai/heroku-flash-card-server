package com.example.users;

public interface UserDao {
	public User verifyUser(String userName);
	public void createUser(String newUser);
	public void deleteUser(int userId);
}
