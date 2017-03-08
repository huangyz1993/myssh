package com.user.service;

import com.user.entity.Users;

public interface UserService {
	
	public void addUser(Users user);
	
	public Users findById(String id);
	
	public void deleteUser(String id);
	
	public void updateUser(Users user);

}
