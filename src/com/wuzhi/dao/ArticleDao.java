package com.wuzhi.dao;

import java.util.List;
import java.util.Map;

import com.wuzhi.entity.Articles;

public interface ArticleDao {
	public List<Articles> getArticles();
	
	public List<Articles> getArticles(String keywords);

	public List<Articles> getUserArticles(String userId, String years, String months);

	public Map getOneWeakNum(String time,String time1,String time2,String time3,String time4,String time5,String time6);

	public List<Articles> getArticlesByTime(String time);
	
	public boolean findByUserAndTime(String userId, String time,String content);
}
