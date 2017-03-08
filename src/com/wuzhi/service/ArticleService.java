package com.wuzhi.service;

import java.util.List;

import com.wuzhi.entity.Articles;

public interface ArticleService {
	public List<Articles> getArticles();

	public List<Articles> getUserArticles(String userId, String years, String months);
}
