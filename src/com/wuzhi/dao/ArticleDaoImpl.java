package com.wuzhi.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.wuzhi.entity.Articles;

@Repository("articlesDao")
public class ArticleDaoImpl extends BaseDao implements ArticleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Articles> getArticles() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Articles order by time desc").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Articles> getArticles(String keywords) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Articles where article like '%" + keywords + "%' order by time desc")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Articles> getUserArticles(String userId, String years, String months) {
		// TODO Auto-generated method stub
		if (years == null) {
			Calendar a = Calendar.getInstance();
			years = a.get(Calendar.YEAR) + "";
			if (a.get(Calendar.MONTH) + 1 < 10) {
				months = "0" +( a.get(Calendar.MONTH) + 1);
			} else {
				months = a.get(Calendar.MONTH) + 1 + "";
			}
		}
		return getSession().createQuery(
				"from Articles where time like '%" + years + "-" + months + "%' and userid='" + userId
						+ "' order by time desc").list();
	}

}
