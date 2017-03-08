package com.wuzhi.dao;

import java.util.List;

import com.wuzhi.entity.User;

public interface WuzhiDao {
	public void insertWuzhiUser(User obj);
	public void updateWuzhiUser(User obj);
	public User findByUserId(String userId);
	public List<User> findByUserIds(String userIds);
	public List<User> getAllUser();

}
