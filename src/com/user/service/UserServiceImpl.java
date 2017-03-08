package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dao.UserDao;
import com.user.entity.Users;



@Service("service")
public class UserServiceImpl implements com.user.service.UserService{
	
	@Autowired
	public UserDao dao;

	@Override
	public void addUser(Users user) {
		// TODO Auto-generated method stub
		dao.AddUser(user);
		
	}

	@Override
	public Users findById(String id) {
		// TODO Auto-generated method stub
		return (Users) dao.get(Users.class,"id",id);
	}

	@Override
	public void deleteUser(String id) {
		dao.deleteById(id);
		
	}

	@Override
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		dao.update(user);
	}

}
