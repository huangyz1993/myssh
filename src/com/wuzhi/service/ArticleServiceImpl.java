package com.wuzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuzhi.dao.ArticleDao;
import com.wuzhi.entity.Articles;



@Service("articlesService")
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleDao articlesDao;


	@Override
	public List<Articles> getArticles() {
		// TODO Auto-generated method stub
		return articlesDao.getArticles();
	}


	@Override
	public List<Articles> getUserArticles(String userId, String years, String months) {
		// TODO Auto-generated method stub
		return articlesDao.getUserArticles(userId,years,months);
	}

}
