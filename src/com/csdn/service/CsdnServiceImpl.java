package com.csdn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csdn.dao.CsdnDao;
import com.csdn.entity.Csdn;

@Service("csdnService")
public class CsdnServiceImpl implements CsdnService {

	@Autowired
	private CsdnDao csdnDao;

	@Override
	public void insertCsdn(Csdn csdn) {
		// TODO Auto-generated method stub
		csdnDao.insertCsdn(csdn);
	}

	@Override
	public List<Csdn> getCsdn() {
		// TODO Auto-generated method stub
		return csdnDao.getCsdn();
	}

}
