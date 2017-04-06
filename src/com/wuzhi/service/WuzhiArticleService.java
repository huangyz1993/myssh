package com.wuzhi.service;

import java.util.List;
import java.util.Map;

import com.wuzhi.entity.Article;
import com.wuzhi.entity.Articles;
import com.wuzhi.view.UserArticle;

public interface WuzhiArticleService {

	public void add(Article article);

	public boolean isExist(String userId, String time, String content);

	public List<UserArticle> getUserArticles(String userId, String year, String month, String date);

	public List<Article> getTrend(String keyword);

	public Integer getByKeyword(String keyword, String time);

	public List<Articles> getArticles(String keyword);

	public List<Articles> getArticles();

	public List<Articles> getUserArticles(String userId, String years, String months);

	public Map getOneWeakNum(String time,String time1,String time2,String time3,String time4,String time5,String time6);

	public List<Articles> getArticlesByTime(String time);

}
