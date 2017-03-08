package com.wuzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuzhi.dao.WuzhiDao;
import com.wuzhi.entity.User;


@Service("wuzhiService")
public class WuzhiServiceImpl implements WuzhiService {
	
	@Autowired
	private WuzhiDao wuzhiDao;

	@Override
	public void InsertUSer(User obj) {
		// TODO Auto-generated method stub
		wuzhiDao.insertWuzhiUser(obj);
	}

	@Override
	public void updateUser(User obj) {
		// TODO Auto-generated method stub
		wuzhiDao.updateWuzhiUser(obj);
	}

	@Override
	public User findUser(String userId) {
		// TODO Auto-generated method stub
		return wuzhiDao.findByUserId(userId);
	}
	
	@Override
	public List<User> findUsers(String userIds) {
		// TODO Auto-generated method stub
		return wuzhiDao.findByUserIds(userIds);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return wuzhiDao.getAllUser();
	}

}
