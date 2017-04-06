package com.csdn.service;

import java.util.List;

import com.csdn.entity.Csdn;

public interface CsdnService {
	 public void insertCsdn(Csdn csdn);
	   public List<Csdn> getCsdn();
}
