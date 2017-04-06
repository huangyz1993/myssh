package com.wuzhi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuzhi.dao.ArticleDao;
import com.wuzhi.dao.WuzhiArticleDao;
import com.wuzhi.entity.Article;
import com.wuzhi.entity.Articles;
import com.wuzhi.view.UserArticle;

@Service("wuzhiArticleService")
public class WuzhiArticleServiceImpl implements WuzhiArticleService {

	@Autowired
	private WuzhiArticleDao wuzhiArticleDao;

	@Autowired
	private ArticleDao articlesDao;

	@Override
	public void add(Article article) {
		wuzhiArticleDao.insertArticle(article);
	}

	@Override
	public boolean isExist(String userId, String time, String content) {
		// TODO Auto-generated method stub
		return articlesDao.findByUserAndTime(userId, time, content);
	}

	@Override
	public List<UserArticle> getUserArticles(String userId, String year, String month, String date) {
		// TODO Auto-generated method stub
		return wuzhiArticleDao.getUserArticles(userId, year, month, date);
	}

	@Override
	public List<Article> getTrend(String keyword) {
		// TODO Auto-generated method stub
		return wuzhiArticleDao.getTrend(keyword);
	}

	@Override
	public Integer getByKeyword(String keyword, String time) {
		// TODO Auto-generated method stub
		return wuzhiArticleDao.getByKeyword(keyword, time);
	}

	@Override
	public List<Articles> getArticles(String keyword) {
		// TODO Auto-generated method stub
		return articlesDao.getArticles(keyword);
	}

	@Override
	public List<Articles> getArticles() {
		// TODO Auto-generated method stub
		return articlesDao.getArticles();
	}

	@Override
	public List<Articles> getUserArticles(String userId, String years, String months) {
		// TODO Auto-generated method stub
		return articlesDao.getUserArticles(userId, years, months);
	}

	@Override
	public Map getOneWeakNum(String time,String time1,String time2,String time3,String time4,String time5,String time6) {
		// TODO Auto-generated method stub
		return articlesDao.getOneWeakNum(time,time1,time2,time3,time4,time5,time6);
	}

	@Override
	public List<Articles> getArticlesByTime(String time) {
		// TODO Auto-generated method stub
		return articlesDao.getArticlesByTime(time);
	}

}
