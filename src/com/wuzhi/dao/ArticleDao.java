package com.wuzhi.dao;

import java.util.List;

import com.wuzhi.entity.Articles;

public interface ArticleDao {
	public List<Articles> getArticles();
	
	public List<Articles> getArticles(String keywords);

	public List<Articles> getUserArticles(String userId, String years, String months);
}
