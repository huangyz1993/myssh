package com.wuzhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuzhi.dao.AddNumDao;
import com.wuzhi.entity.AddNum;

@Service("addNumService")
public class AddNumServiceImpl implements AddNumService {
	@Autowired
	public AddNumDao addNumDao;

	@Override
	public void insert(AddNum addNum) {
		// TODO Auto-generated method stub
		addNumDao.insert(addNum);
	}

}
