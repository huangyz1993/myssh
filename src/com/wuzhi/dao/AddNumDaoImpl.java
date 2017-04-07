package com.wuzhi.dao;

import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.wuzhi.entity.AddNum;

@Repository("addNumDao")
public class AddNumDaoImpl extends BaseDao implements AddNumDao {

	@Override
	public void insert(AddNum addNum) {
		// TODO Auto-generated method stub
		getSession().save(addNum);
	}

}
