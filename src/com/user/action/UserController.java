package com.user.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.user.entity.Users;

@Controller("loginAction")
public class UserController  {
	public String username;
	public String password;
	public Users user;
	public String id;
	
	@Autowired
	public com.user.service.UserService service;

	public String execute() {
		Users user=new Users();
		user.setAge(1);
		user.setName("2");
		user.setPassword("3");
		service.addUser(user);
		return "success";
	}

	public void  deleteUser(){
		service.deleteUser(id);
	}
	
	
	public String showUser(){
		user=service.findById("8a88ce795606ebd4015606ec454c0000");
		return "show";
	}
	
	public void updateUser(){
		user.setAge(222);
		service.updateUser(user);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
