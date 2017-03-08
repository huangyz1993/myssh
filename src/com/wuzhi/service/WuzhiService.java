package com.wuzhi.service;

import java.util.List;

import com.wuzhi.entity.User;

public interface WuzhiService {
   public void InsertUSer(User obj);
   public void updateUser(User obj);
   public User findUser(String userId);
   public List<User> findUsers(String userIds);
   public List<User> getAllUser();
}
