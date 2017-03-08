package com.wuzhi.service;

import java.util.List;

import com.wuzhi.entity.Article;
import com.wuzhi.entity.Articles;
import com.wuzhi.view.UserArticle;

public interface WuzhiArticleService {

	public void add(Article article);

	public boolean isExist(String userId, String time, String content);

	public List<Article> getArticles();

	public List<UserArticle> getUserArticles(String userId,String year,String month,String date);

	public List<Article> getTrend(String keyword);

	public Integer getByKeyword(String keyword,String time);
	
	public List<Articles> getArticles(String keyword);
}
