package com.wuzhi.dao;

import java.util.List;

import com.wuzhi.entity.Article;
import com.wuzhi.entity.User;
import com.wuzhi.view.UserArticle;

public interface WuzhiArticleDao {
	/**
	 * 
	 * @param article
	 */
	public void insertArticle(Article article);

	/**
	 * 
	 * @param userId
	 * @param time
	 * @param content
	 * @return
	 */
	public boolean findByUserAndTime(String userId, String time,String content);
	
	
	
	public List<Article> getArticles();
	
	
	public List<UserArticle> getUserArticles(String userId,String year,String month,String date);
	
	
	public List<Article> getTrend(String keyword);

	public Integer getByKeyword(String keyword,String time);
}
