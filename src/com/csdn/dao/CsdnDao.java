package com.csdn.dao;

import java.util.List;

import com.csdn.entity.Csdn;

public interface CsdnDao {
   public void insertCsdn(Csdn csdn);
   public List<Csdn> getCsdn();
}
