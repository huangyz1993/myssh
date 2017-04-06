package com.csdn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.csdn.entity.Csdn;


@Repository("csdnDao")
public class CsdnDaoImpl extends BaseDao implements CsdnDao{

	@Override
	public void insertCsdn(Csdn csdn) {
		// TODO Auto-generated method stub
		getSession().save(csdn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Csdn> getCsdn() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Csdns").list();
	}

}
