package com.wuzhi.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.wuzhi.entity.Articles;

@Repository("articlesDao")
public class ArticleDaoImpl extends BaseDao implements ArticleDao {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Articles> getArticles() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Articles order by time desc").list();
	}
	
	@Override
	public boolean findByUserAndTime(String userId, String time, String content) {
		if (getSession().createQuery("from Articles where userid='" + userId + "' and time='" + time + "'").list()
				.size() > 0) {
			return true;
		} else {
			return false;
		}
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
				months = "0" + (a.get(Calendar.MONTH) + 1);
			} else {
				months = a.get(Calendar.MONTH) + 1 + "";
			}
		}
		return getSession().createQuery(
				"from Articles where time like '%" + years + "-" + months + "%' and userid='" + userId
						+ "' order by time desc").list();
	}

	@Override
	public Map getOneWeakNum(String time, String time1, String time2, String time3, String time4, String time5,
			String time6) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForMap("select sum(case when  t.time like '" + time
				+ "%' then 1 else 0 end) as '1',sum(case when  t.time like '" + time1
				+ "%' then 1 else 0 end) as '2',sum(case when  t.time like '" + time2
				+ "%' then 1 else 0 end) as '3',sum(case when  t.time like '" + time3
				+ "%' then 1 else 0 end) as '4',sum(case when  t.time like '" + time4
				+ "%' then 1 else 0 end) as '5',sum(case when  t.time like '" + time5
				+ "%' then 1 else 0 end) as '6',sum(case when  t.time like '" + time6
				+ "%' then 1 else 0 end) as '7' from article t");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Articles> getArticlesByTime(String time) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Articles where time like '%" + time + "%' order by time desc").list();
	}
}
